<template>
  <NuxtLayout>
    <NuxtPage></NuxtPage>
  </NuxtLayout>
</template>

<script setup>
const config = useRuntimeConfig().public;

onMounted(() => {
  useHead({
    // rel: 'icon', type: 'image/x-icon', href: config.IMAGE_BASE_URL + favicon_location.value,
    // link: [{rel: 'icon', type: 'image/x-icon', href: `${config.IMAGE_BASE_URL + favicon_location.value}`}],
    title: config.APP_NAME + " Portal",
    viewport: "width=device-width, initial-scale=1, maximum-scale=1",
    charset: "utf-8",
    meta: [
      {name: 'google-site-verification', content: '080LVSqbz2eadUMq4xUbQ3HWFq7OIeuNnuVrKfOG2s0'},
    ],
    script: [
      {
        src: 'https://www.googletagmanager.com/gtag/js?id=G-7SPNGNSQKM',
        async: true,
      },
      {
        children: "   window.dataLayer = window.dataLayer || [];   function gtag(){dataLayer.push(arguments);}   gtag('js', new Date());   gtag('config', 'G-7SPNGNSQKM');",
      },
      {
        children: "(function(w,d,s,l,i){w[l]=w[l]||[];w[l].push({'gtm.start':\n" +
            "              new Date().getTime(),event:'gtm.js'});var f=d.getElementsByTagName(s)[0],\n" +
            "            j=d.createElement(s),dl=l!='dataLayer'?'&l='+l:'';j.async=true;j.src=\n" +
            "            'https://www.googletagmanager.com/gtm.js?id='+i+dl;f.parentNode.insertBefore(j,f);\n" +
            "        })(window,document,'script','dataLayer','GTM-THW497G3');",
      }
    ],
    noscript: [
      {
        innerHTML: '<iframe src="https://www.googletagmanager.com/ns.html?id=GTM-THW497G3" height="0" width="0" style="display:none;visibility:hidden"></iframe>',
        tagPosition: 'bodyOpen',
      },
    ]
  });
})
</script>