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
const labels = ref([]);
let listsToShow = ref([]);
const activeFilter = ref(null);

watch(user, () => {
  if (user.value) {
    gamesList = useGraphql(
        'gamesListByUserId',
        {userId: user.value.id},
        ['game {name}', 'label']);
    watch(gamesList, () => {
      labels.value = [...new Set(gamesList.value.map((game) => game.label))];

      activeFilter.value = labels.value.find(label => route.params.filter === label) || 'All';

      listsToShow = computed(() => {
        return activeFilter.value === 'All' ?
            labels.value :
            labels.value.filter((label) => label === activeFilter.value);
      });
    });
  } else {
    router.push({name: 'notfound'})
  }
});

function selectFilter(tabName) {
  activeFilter.value = tabName;
  if (tabName === 'All') {
    router.replace({name: 'userlist', params: {username: userName}});
  } else {
    router.replace({name: 'userlistfilter', params: {username: userName, filter: tabName}});
  }
}

</script>
<template>
  <div class="container">
    <h3 v-if="user">{{ user.name }}</h3>
    <div class="row">
      <div class="col-2">
        <FilterByLabel v-if="gamesList && activeFilter" :labels="labels" :active-tab="activeFilter"
                       @tab-activated="selectFilter"/>
      </div>
      <div class="col-10">
        <ListGroup v-if="listsToShow.length" v-for="label in listsToShow" :label="label"
                   :entries="gamesList.filter((entry) => entry.label === label)"/>
        <h3 class="text-center align-middle" v-else>Nothing to show...</h3>
      </div>
    </div>
  </div>
</template>
