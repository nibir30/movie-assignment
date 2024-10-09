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

const {login, register} = userService();

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
const showRegisterModal = ref(false);

const form = reactive({
  email: '',
  firstName: '',
  lastName: '',
  gender: '',
  dob: '',
})
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

async function doRegister() {
  isLoading.value = true

  try {
    const payload = {
      email: form.email.trim(),
      firstName: form.firstName,
      lastName: form.lastName,
      dob: form.dob,
      gender: form.gender,
    }
    let response = await register(payload);

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
  showRegisterModal.value = false
  clear()
}

function goToRegister(value) {
  clear()
  showRegisterModal.value = value
}


function clear() {
  form.email = ''
  form.firstName = ''
  form.lastName = ''
  form.dob = ''
  form.gender = ''
  email.value = ''
}
</script>

<template>
  <div>
    <Dialog :open="open" @update:open="changeOpen">
      <DialogContent v-if="!showRegisterModal" class="sm:max-w-[425px]">
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
        <DialogFooter class="flex flex-row gap-x-2">
          <Button variant="outline" @click="goToRegister(true)">
            <div>
              Or, Register
            </div>
          </Button>
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
      <DialogContent v-else class="sm:max-w-[425px]">
        <DialogHeader>
          <DialogTitle>Sign Up</DialogTitle>
          <DialogDescription>
            Welcome to MOVIESTAR. Please sign up to continue...
          </DialogDescription>
        </DialogHeader>
        <div class="grid gap-4 py-4">
          <div class="grid grid-cols-4 items-center gap-4">
            <Label class="text-right" for="name">
              Email
            </Label>
            <Input id="name" v-model="form.email" class="col-span-3"/>
          </div>
          <div class="grid grid-cols-4 items-center gap-4">
            <Label class="text-right" for="name">
              First Name
            </Label>
            <Input id="name" v-model="form.firstName" class="col-span-3"/>
          </div>
          <div class="grid grid-cols-4 items-center gap-4">
            <Label class="text-right" for="name">
              Last Name
            </Label>
            <Input id="name" v-model="form.lastName" class="col-span-3"/>
          </div>
          <div class="grid grid-cols-4 items-center gap-4">
            <Label class="text-right" for="name">
              Gender
            </Label>
            <Input id="name" v-model="form.gender" class="col-span-3"/>
          </div>
          <div class="grid grid-cols-4 items-center gap-4">
            <Label class="text-right" for="name">
              Birthday
            </Label>
            <Input id="name" v-model="form.dob" class="col-span-3"/>
          </div>
        </div>

        <DialogFooter class="flex flex-row gap-x-2">
          <Button variant="outline" @click="goToRegister(false)">
            <div>
              Or, Login
            </div>
          </Button>
          <Button @click="doRegister">
            <div v-if="isLoading">
              <Skeleton class="w-[40px] h-5 rounded-full"/>
            </div>
            <div v-else>
              Sign Up
            </div>
          </Button>
        </DialogFooter>
      </DialogContent>
    </Dialog>
  </div>

</template>

<style scoped>

</style>