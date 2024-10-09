import {ref} from 'vue';

export const useUser = () => {
    const username = ref('Guest');

    // Function to set user data in localStorage and update the username
    const setUser = (userData) => {
        localStorage.setItem('user', JSON.stringify(userData));
        username.value = userData.fullName;
    };

    // Function to load user from localStorage on page load
    const loadUser = () => {
        const storedUser = localStorage.getItem('user');
        if (storedUser) {
            username.value = JSON.parse(storedUser).fullName;
        }
    };

    return {username, setUser, loadUser};
};
