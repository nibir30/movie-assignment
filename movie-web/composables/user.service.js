export default () => {
    const config = useRuntimeConfig().public;

    const login = (param) => {
        return new Promise(async (resolve, reject) => {
            try {
                const data = await $fetch(config.API_BASE_URL + '/user/login', {
                    method: 'POST',
                    params: param,
                })
                resolve(data || [])
            } catch (error) {
                reject(error)
            }
        })
    }
    const register = (body) => {
        return new Promise(async (resolve, reject) => {
            try {
                const data = await $fetch(config.API_BASE_URL + '/user/register', {
                    method: 'POST',
                    body: body,
                })
                resolve(data || [])
            } catch (error) {
                reject(error)
            }
        })
    }

    return {
        register,
        login,
    }
}