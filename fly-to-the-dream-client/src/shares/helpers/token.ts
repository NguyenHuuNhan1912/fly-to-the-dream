

const tokenHelper = {
    setToken: (token: string) => {
        document.cookie = `token=${JSON.stringify(token)}; expires=${new Date('2025-1-29 10:00:00').toUTCString()}`;
    },
    removeToken: () => {
        document.cookie = `token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;`;
    }
}

export default tokenHelper;


