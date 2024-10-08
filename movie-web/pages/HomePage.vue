<script lang="ts" setup>
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
import {Button} from '@/components/ui/button'

</script>

<template>
  <div>
    <div class="underline">
      Home Page
    </div>
    <nuxt-link to="/SecondLayoutDemoPage">
      <Button>Other Page</Button>
    </nuxt-link>
    <font-awesome-icon icon="fa-solid fa-user-secret"/>
  </div>
</template>

<style scoped>

</style>