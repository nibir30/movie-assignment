<script setup>

import {FontAwesomeIcon} from "@fortawesome/vue-fontawesome";
import {Sheet, SheetContent, SheetHeader, SheetTitle, SheetTrigger,} from '@/components/ui/sheet'

const router = useRouter()
onMounted(() => {
  const user = localStorage.getItem('user')
  if (user) {
    toggleUser(JSON.parse(user))
  }
  window.addEventListener('user-changed', (event) => {
    const user = event.detail.user
    toggleUser(user)
  });
});

const showLogin = ref(false)
const userName = ref('')
const userAvailable = ref(false)
const userInfo = ref(null)

function updateLoginOpen(value) {
  showLogin.value = value;
}

function toggleUser(user) {
  if (user == null) {
    userAvailable.value = false
    userName.value = ''
    userInfo.value = null
  } else {
    userAvailable.value = true
    userName.value = user.fullName
    userInfo.value = user
  }
}

async function logout() {
  window.dispatchEvent(new CustomEvent('user-changed', {
    detail: {
      user: null
    }
  }));
  localStorage.clear()
  toggleUser(null)
  await router.push({path: "/"})
}

async function goToWishList() {
  if (userInfo.value) {
    await router.push({path: "/my-favorites"})
  } else {
    showLogin.value = true
  }
}

async function goToHome() {
  await router.push({path: "/"})
}
</script>

<template>
  <div class="px-5 py-2 flex flex-row bg-gray-800 text-white gap-x-2 items-center justify-between">
    <div class="font-bold flex-1 text-3xl cursor-pointer" @click="goToHome">
      MOVIESTAR
    </div>
    <!--    <Input class="hidden md:flex max-w-96 text-black" placeholder="Search for movies, stars, categories..."></Input>-->

    <div class="flex flex-row gap-x-2 items-center cursor-pointer hover:bg-gray-700 rounded p-2 border border-gray-700 "
         @click="goToWishList">
      <font-awesome-icon class="" icon="fa-solid fa-star"/>
      <div class="hidden md:flex font-bold text-lg" to="/my-favorites">My Favorites</div>
    </div>
    <Button class="visible md:hidden text-black" size="sm" variant="secondary" @click="goToWishList">
      <font-awesome-icon icon="fa-solid fa-magnifying-glass"/>
    </Button>

    <div v-if="userName === ''"
         class="rounded p-2 border border-gray-700 font-bold text-lg cursor-pointer hover:bg-gray-700"
         @click="showLogin = true">
      Sign In
    </div>
    <Sheet v-else>
      <SheetTrigger>
        <Button class="flex flex-row gap-x-2 items-center text-black" size="sm" variant="outline">
          <font-awesome-icon class="" icon="fa-solid fa-user"/>
          <div class="hidden md:flex">
            {{ userName }}
          </div>
        </Button>
      </SheetTrigger>
      <SheetContent class="flex flex-col">
        <SheetHeader>
          <SheetTitle class="text-2xl">Personal Info</SheetTitle>
        </SheetHeader>
        <div>
          Name: <span class="font-semibold text-lg">{{ userInfo.fullName }}</span>
        </div>
        <div>
          Email: <span class="font-semibold text-lg">{{ userInfo.email }}</span>
        </div>
        <div>
          Gender: <span class="font-semibold text-lg">{{ userInfo.gender }}</span>
        </div>
        <div>
          Birthday: <span class="font-semibold text-lg">{{ userInfo.dob }}</span>
        </div>
        <Button class="mt-auto w-full" variant="destructive" @click="logout">
          Logout
        </Button>

      </SheetContent>
    </Sheet>

    <LoginModal :is-open="showLogin" @update:is-open="updateLoginOpen"></LoginModal>

  </div>
</template>

<style scoped>

</style>