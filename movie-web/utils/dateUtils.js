export function get_year(value) {
    if (value) {
        const date = new Date(value);
        return date.getFullYear();
    } else {
        return ''
    }
}