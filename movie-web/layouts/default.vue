<script setup>
import Header from "~/layouts/partial/Header.vue";

const config = useRuntimeConfig().public;


onMounted(async () => {
});

useHead({
  htmlAttrs: {
    lang: 'en',
  },
})
</script>


<template>
  <div>
    <!--     Header Here-->
    <Header></Header>
    <div class="flex flex-row">
      <slot/>
      <!--        Sidebar here-->
      <!--      <div class="border p-5">DEFAULT SIDEBAR</div>-->
    </div>
    <!--      Footer Here-->
    <!--    <div class="w-full border p-5">DEFAULT FOOTER</div>-->
  </div>
</template>

<style lang="css" scoped>
.lkl {
  color: black;
}
</style>