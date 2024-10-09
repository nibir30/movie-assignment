export default defineNuxtConfig({
    devtools: {enabled: false},
    css: [
        '~/assets/css/main.css',
        '@fortawesome/fontawesome-svg-core/styles.css'
    ],
    modules: [
      '@nuxtjs/tailwindcss',
      'shadcn-nuxt',
      '@samk-dev/nuxt-vcalendar',
    ],
    shadcn: {
        prefix: '',
        componentDir: './components/ui'
    },
    postcss: {
        plugins: {
            tailwindcss: {},
            autoprefixer: {},
        },
    },
    runtimeConfig: {
        public: {
            BACKEND_URL: process.env.BACKEND_URL,
            APP_NAME: process.env.APP_NAME,
            API_BASE_URL: process.env.API_BASE_URL,
        }
    },
})