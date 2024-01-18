<script setup>
import {computed, ref, watch} from "vue";
import {useRoute, useRouter} from 'vue-router'
import FilterByLabel from "./FilterByLabel.vue"
import ListGroup from "./ListGroup.vue";
import useGraphql from "../composables/graphql";

const router = useRouter()
const route = useRoute();

const userName = route.params.username;

const user = useGraphql(
    'userByName',
    {name: userName},
    ['id', 'name']);

let gamesList = ref([]);
let labels = ref([]);
let listsToShow = ref([]);

watch(user, (u) => {
   gamesList = useGraphql(
       'gamesListByUserId',
      {userId: u.id},
      ['game {name}', 'label']);
  labels = computed(() => {
    return [...new Set(gamesList.value.map((game) => game.label))];
  });
  listsToShow = computed(() => {
    return activeFilter.value === 'All' ?
        labels.value :
        labels.value.filter((label) => label === activeFilter.value);
  });
});

let filterFromRoute = route.params.filter;
const activeFilter = ref(filterFromRoute ? filterFromRoute : 'All');
function selectFilter(tabName) {
  activeFilter.value = tabName;
  router.replace({name: 'userlistfilter', params: {username: userName, filter: tabName}})
}

</script>
<template>
  <div class="container">
    <h3 v-if="user">{{ user.name }}</h3>
    <div class="row">
      <div class="col-2">
        <FilterByLabel v-if="gamesList" :labels="labels" :active-tab="activeFilter" @tab-activated="selectFilter" />
      </div>
      <div class="col-10">
        <ListGroup v-for="label in listsToShow" :label="label"
          :entries="gamesList.filter((entry) => entry.label === label)" />
      </div>
    </div>
  </div>
</template>
