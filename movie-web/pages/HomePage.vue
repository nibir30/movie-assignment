<script setup>
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
import {Button} from '@/components/ui/button'
import {get_year} from "~/utils/dateUtils.js";
import {Badge} from "~/components/ui/badge";

const {getMoviesPaginated} = movieService();

onMounted(async () => {
  await fetchMovies()
});

const isLoading = ref(true)
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
</script>

<template>
  <div class="mx-16 lg:mx-28 my-4 flex flex-col w-full">
    <div class="font-bold text-2xl">MOVIESTAR Charts</div>

    <div class="mt-5 border rounded-xl w-full grid md:grid-cols-2 lg:grid-cols-3 gap-2 p-4">
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
          <Button class="mt-2 bg-red-500 text-white px-4 justify-center flex flex-row gap-x-2">
            <font-awesome-icon class="" icon="fa-solid fa-magnifying-glass"/>
            <div>Add</div>
          </Button>
        </div>
      </div>

    </div>
    <!--    <div class="underline">-->
    <!--      Home Page-->
    <!--    </div>-->
    <!--    <nuxt-link to="/SecondLayoutDemoPage">-->
    <!--      <Button>Other Page</Button>-->
    <!--    </nuxt-link>-->
    <!--    <font-awesome-icon icon="fa-solid fa-user-secret"/>-->
  </div>
</template>

<style scoped>

</style>