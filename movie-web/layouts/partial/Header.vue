<script setup>

import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
</script>

<template>
  <div class="px-5 py-2 flex flex-row bg-gray-800 text-white gap-x-2 items-center justify-between">
    <div class="font-bold flex-1 text-3xl">
      MOVIESTAR
    </div>
    <div class="hidden md:flex max-w-96 w-full relative items-center">
      <Input id="search" class="pl-8 text-black" placeholder="Search for movies, stars, categories..." type="text"/>
      <span class="absolute start-0 inset-y-0 flex items-center justify-center px-2">
      <font-awesome-icon class="text-black" icon="fa-solid fa-magnifying-glass"/>
    </span>
    </div>
    <!--    <Input class="hidden md:flex max-w-96 text-black" placeholder="Search for movies, stars, categories..."></Input>-->
    <div class="visible md:hidden">
      <font-awesome-icon icon="fa-solid fa-magnifying-glass"/>
    </div>
    <div
        class="flex flex-row gap-x-2 items-center cursor-pointer hover:bg-gray-700 rounded p-2 border border-gray-700 ">
      <font-awesome-icon class="" icon="fa-solid fa-star"/>
      <div class="font-bold text-lg">My Favorites</div>
    </div>
    <div class="rounded p-2 border border-gray-700 font-bold text-lg cursor-pointer hover:bg-gray-700">
      Sign In
    </div>
  </div>
</template>

<style scoped>

</style>