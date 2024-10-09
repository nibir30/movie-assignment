<script setup>
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
import {Button} from '@/components/ui/button'
import {get_year} from "~/utils/dateUtils.js";
import {Badge} from "~/components/ui/badge";
import Header from "~/layouts/partial/Header.vue";
import {
  Dialog,
  DialogContent,
  DialogTrigger
} from "~/components/ui/dialog";

const {addToFavorite, getFavoriteMovies} = movieService();

onMounted(async () => {
  const user = localStorage.getItem("user");
  if (user) {
    userInfo.value = JSON.parse(user);
  }
  await fetchMovies()
});

const isLoading = ref(true)
const showLogin = ref(false)
const userInfo = ref(null)
const favoriteMovieIds = ref([])
const allMovies = ref([])
const search = ref('')

const page = ref(1)


async function fetchMovies() {
  isLoading.value = true
  try {
    const payload = {
      email: userInfo.value.email,
      search: search.value,
    }
    const response = await getFavoriteMovies(payload);
    isLoading.value = false

    if (response.success) {
      allMovies.value = response.data
    }

  } catch (e) {
    isLoading.value = false
    console.log(e)
  }
}

async function addMovieToFavorite(movieID, addFavorite) {
  let user = localStorage.getItem("user");
  if (user) {
    user = JSON.parse(user)
    const payload = {
      "email": user.email,
      "movieId": movieID,
      "addFavorite": addFavorite
    }

    const response = await addToFavorite(payload);

    if (response && response.success) {
      useNuxtApp().$toast.success(response.message);
      if (addFavorite) {
        userInfo.value.favoriteMovieIds.push(movieID)
      } else {
        userInfo.value.favoriteMovieIds = userInfo.value.favoriteMovieIds.filter(movieId => movieId !== movieID);
        localStorage.setItem("user", JSON.stringify(userInfo.value));
      }
      await fetchMovies();
    } else {
      useNuxtApp().$toast.error(response.message);
    }
  } else {
    showLogin.value = true
  }
}

function updateLoginModal(value) {
  showLogin.value = value
}

watch(search, async () => {
  if (search.value === '' || search.value.length > 2) {
    await fetchMovies();
  }
})
</script>

<template>
  <div class="w-full">
    <Header></Header>
    <div class="mx-4 sm:mx-16 lg:mx-28 my-4 flex flex-col">
      <div class="flex flex-wrap gap-x-4 justify-between items-center">
        <div class="font-bold text-2xl">Your Favorites</div>

        <div class="max-w-96 w-full relative items-center">
          <Input id="search" v-model="search" class="pl-8 text-black"
                 placeholder="Search for movies, stars, categories..." type="text"/>
          <span class="absolute start-0 inset-y-0 flex items-center justify-center px-2">
            <font-awesome-icon class="text-black" icon="fa-solid fa-magnifying-glass"/>
          </span>
        </div>
      </div>
      <div v-if="allMovies.length === 0 && search !== ''" class="mt-14 border rounded-xl p-4 font-bold text-red-700">
        NO MOVIES FOUND RELATED '{{ search }}'
      </div>
      <div v-else-if="allMovies.length === 0"
           class="mt-14 border rounded-xl p-4 font-bold text-yellow-700">
        YOU HAVEN'T ADDED ANY MOVIE AS YOUR FAVORITE
      </div>
      <div v-else class="mt-5 border rounded-xl grid md:grid-cols-2 lg:grid-cols-3 gap-2 p-4">
        <div v-for="(movie,index) in allMovies" class="border flex flex-row">
          <img :alt="movie.title" :src="movie.imagePath" class="object-cover w-1/3 h-full"/>
          <div class="flex flex-col w-full p-2">
            <div v-if="movie.categories && movie.categories.length > 0"
                 class="flex flex-row flex-wrap gap-x-2 gap-y-1">
              <div v-for="category in movie.categories" class="capitalize">
                <Badge class="bg-yellow-700">
                  {{ category.name }}
                </Badge>
              </div>
            </div>
            <div class="font-bold text-lg mt-2">
              {{ index + 1 }}. {{ movie.title }}
              <span class="text-gray-600 font-medium text-sm">
              ({{ get_year(movie.releaseDate) }})
            </span>
            </div>

            <div v-if="movie.casts && movie.casts.length > 0" class="flex mt-1 flex-row flex-wrap gap-x-2 gap-y-1">
              <div>Cast:</div>
              <div v-for="(cast,index) in movie.casts" class="capitalize text-blue-900 font-semibold">
                {{ cast.fullName }} <span v-if="index !== movie.casts.length - 1">,</span>
              </div>
            </div>

            <div class="mt-1">Budget: <span class="text-green-800 font-semibold">{{ movie.budget }}</span></div>

            <div class=" md:mt-auto flex flex-wrap gap-x-2">
              <Dialog>
                <DialogTrigger>
                  <Button class="mt-4 text-white px-4 justify-center flex flex-row gap-x-2 w-24" size="xs">
                    <div>View</div>
                  </Button>
                </DialogTrigger>
                <DialogContent class="sm:max-w-[425px] md:min-w-[50%] overflow-auto max-h-[80vh]">
                  <div class="flex flex-col">
                    <div class="flex flex-row">
                      <img :alt="movie.title" :src="movie.imagePath" class="object-cover max-h-[30vh]"/>
                      <div class="pl-4 flex flex-col justify-between">
                        <div class="font-bold text-2xl">
                          {{ movie.title }}
                        </div>

                        <div v-if="movie.categories && movie.categories.length > 0"
                             class="flex flex-row flex-wrap gap-x-2 gap-y-1">
                          <div v-for="category in movie.categories" class="capitalize">
                            <Badge class="bg-yellow-700">
                              {{ category.name }}
                            </Badge>
                          </div>
                        </div>

                        <div class="text-gray-600 font-medium text-lg">
                          Release Date: {{ movie.releaseDate }}
                        </div>

                        <div class="text-gray-600 font-medium text-lg">
                          Budget: {{ movie.budget }}
                        </div>


                        <div v-if="movie.casts && movie.casts.length > 0"
                             class="flex flex-row flex-wrap gap-x-2 gap-y-1">
                          <div>Cast:</div>
                          <div v-for="(cast,index) in movie.casts" class="capitalize text-blue-900 font-semibold">
                            {{ cast.fullName }} <span v-if="index !== movie.casts.length - 1">,</span>
                          </div>
                        </div>
                      </div>
                    </div>

                    <div class="text-gray-800 text-lg mt-4 border rounded p-2">
                      {{ movie.description }}
                    </div>

                  </div>
                </DialogContent>
              </Dialog>

              <Button class="mt-4 text-white px-4 justify-center flex flex-row gap-x-2"
                      size="xs" variant="destructive" @click="addMovieToFavorite(movie.movieId,false)">
                <font-awesome-icon icon="fa-solid fa-star"/>
              </Button>
            </div>


          </div>
        </div>
      </div>
    </div>
    <LoginModal :is-open="showLogin" @update:is-open="updateLoginModal"></LoginModal>
  </div>
</template>

<style scoped>

</style>