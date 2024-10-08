<script lang="ts" setup>
import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
import {Button} from '@/components/ui/button'

definePageMeta({
  layout: 'only-header-footer-layout'
})
</script>

<template>
  <div>
    <div class="underline">
      With out sidebar layout page
    </div>
    <nuxt-link to="/">
      <Button>Home Page</Button>
    </nuxt-link>
  </div>
</template>

<style scoped>

</style>