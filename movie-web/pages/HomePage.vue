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

const {getMoviesPaginated, addToFavorite} = movieService();

onMounted(async () => {
  await fetchMovies()
});

const isLoading = ref(true)
const showLogin = ref(false)
const allMovies = ref([])

const page = ref(1)


async function fetchMovies() {
  isLoading.value = true
  try {
    const payload = {
      page: page.value,
      per_page: 10,
    }
    const response = await getMoviesPaginated(payload);
    isLoading.value = false

    if (response.success) {
      allMovies.value = response.data.data
    }

  } catch (e) {
    isLoading.value = false
    console.log(e)
  }
}

async function addMovieToFavorite(movieID) {
  let user = localStorage.getItem("user");
  if (user) {
    user = JSON.parse(user)
    const payload = {
      "email": user.email,
      "movieId": movieID,
      "addFavorite": true
    }

    const response = await addToFavorite(payload);

    if (response && response.success) {
      useNuxtApp().$toast.success(response.message);
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
</script>

<template>
  <div class="w-full">
    <Header></Header>
    <div class="mx-4 sm:mx-16 lg:mx-28 my-4 flex flex-col">
      <div class="font-bold text-2xl">MOVIESTAR Charts</div>

      <div class="mt-5 border rounded-xl grid md:grid-cols-2 lg:grid-cols-3 gap-2 p-4">
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

            <div class=" md:mt-auto flex flex-row gap-x-2">
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


              <Button class="mt-4 text-white px-4 justify-center flex flex-row gap-x-2 w-24"
                      size="xs" variant="destructive" @click="addMovieToFavorite(movie.movieId)">
                <font-awesome-icon class="" icon="fa-regular fa-star"/>
                <div>Add</div>
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