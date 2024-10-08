<script setup>
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
    <div class="w-full border p-5">DEFAULT HEADER</div>
    <div class="flex flex-row">
      <div class="flex-1 my-10 p-10">
        <slot/>
      </div>
    </div>
    <!--      Footer Here-->
    <div class="w-full border p-5">DEFAULT FOOTER</div>
  </div>
</template>

<style lang="css" scoped>
.lkl {
  color: black;
}
</style>