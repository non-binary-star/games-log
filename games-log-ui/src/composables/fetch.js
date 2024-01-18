import { ref, toValue, watchEffect } from "vue";

export function useFetch(url, req) {
    const fetchedData = ref(null);

    watchEffect(() => {
        fetchedData.value = null;

        const urlVal = toValue(url);
        const reqVal = toValue(req);

        const res = fetch(urlVal, reqVal)
            .then(resp => resp.json())
            .then(json => fetchedData.value = json);
    });

    return fetchedData;
}
