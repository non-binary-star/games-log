import {useFetch} from "./fetch";
import {GQL_BASE_PATH} from "../components/Props.vue";
import {ref, watch} from "vue";

export default function useGraphql(method, params, fields) {
    const fetchedData = ref({});

    // fetchedData.value = null;
    const computedArgs = Object.keys(params).map(key => `${key}:"${params[key]}"`).join(',');

    const computedQuery = {
        query: `query SomeQuery {${method}(${computedArgs}) {
                ${fields.join(',')}
            }}`
    }

    const fetchRes = useFetch(GQL_BASE_PATH, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(computedQuery)
    });

    watch(fetchRes, (res) => {
        fetchedData.value = res.data[method];
    })
    return fetchedData;
}
