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

    const addToFavorite = (body) => {
        return new Promise(async (resolve, reject) => {
            try {
                const data = await $fetch(config.API_BASE_URL + '/movie/favorite/save', {
                    method: 'POST',
                    body: body
                })
                resolve(data || [])
            } catch (error) {
                reject(error)
            }
        })
    }

    return {
        addToFavorite,
        getMoviesPaginated,
    }
}