export default () => {
    const config = useRuntimeConfig().public;

    const getMoviesPaginated = (param) => {
        return new Promise(async (resolve, reject) => {
            try {
                const data = await $fetch(config.API_BASE_URL + '/movie/paginated', {
                    method: 'GET',
                    params: param
                })
                resolve(data || [])
            } catch (error) {
                reject(error)
            }
        })
    }

    return {
        getMoviesPaginated,
    }
}