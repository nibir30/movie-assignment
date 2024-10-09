<script setup>
import {
  Dialog,
  DialogContent,
  DialogDescription, DialogFooter,
  DialogHeader,
  DialogTitle
} from "~/components/ui/dialog";
import {Skeleton} from '@/components/ui/skeleton'
import {Label} from '@/components/ui/label'

const {login} = userService();

const props = defineProps({
  isOpen: {type: Boolean, required: true, default: false},
});

onMounted(async () => {
  open.value = props.isOpen
})


const emit = defineEmits(["update:isOpen"]);
const open = ref(false);
const isLoading = ref(false);
const email = ref("");
const userInfo = ref(null);

watch(props, async () => {
  open.value = props.isOpen;
})

async function doLogin() {
  isLoading.value = true

  try {
    const payload = {email: email.value.trim()}
    let response = await login(payload);

    isLoading.value = false

    if (response && response.success) {
      userInfo.value = response.data
      // setUser(response.data)
      const movieIds = response.data.favoriteMovies.map(movie => movie.movieId);
      response.data.favoriteMovies = null
      response.data.favoriteMovieIds = movieIds
      localStorage.setItem("user", JSON.stringify(response.data))
      window.dispatchEvent(new CustomEvent('user-changed', {
        detail: {
          user: response.data
        }
      }));
      useNuxtApp().$toast.success(response.message);
      changeOpen(false)
    } else {
      useNuxtApp().$toast.error(response.message);
    }

  } catch (e) {
    console.log(e)
    useNuxtApp().$toast.error(e);
    isLoading.value = false
  }
}

function changeOpen(value) {
  emit('update:isOpen', value);
}
</script>

<template>
  <Dialog :open="open" @update:open="changeOpen">
    <DialogContent class="sm:max-w-[425px]">
      <DialogHeader>
        <DialogTitle>Sign In</DialogTitle>
        <DialogDescription>
          Welcome to MOVIESTAR. Please sign in to continue...
        </DialogDescription>
      </DialogHeader>
      <div class="grid gap-4 py-4">
        <div class="grid grid-cols-4 items-center gap-4">
          <Label class="text-right" for="name">
            Email
          </Label>
          <Input id="name" v-model="email" class="col-span-3"/>
        </div>
      </div>
      <DialogFooter>
        <Button @click="doLogin">
          <div v-if="isLoading">
            <Skeleton class="w-[40px] h-5 rounded-full"/>
          </div>
          <div v-else>
            Sign In
          </div>
        </Button>
      </DialogFooter>
    </DialogContent>
  </Dialog>
</template>

<style scoped>

</style>