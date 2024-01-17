<script setup>
import { ref, computed } from "vue";
import { useRoute } from 'vue-router'
import { GQL_BASE_PATH } from "./Props.vue";
import FilterByLabel from "./FilterByLabel.vue"
import ListGroup from "./ListGroup.vue";

const route = useRoute();

const userName = route.params.username;

const user = ref(null);
const gamesList = ref([]);

fetch(GQL_BASE_PATH, {
  method: "POST",
  headers: {
    "Content-Type": "application/json"
  },
  body: JSON.stringify({
    query: `query SomeQuery {userByName(name: "${userName}") {
      id
    }}`
  })
})
  .then(resp => resp.json())
  .then(json => user.value = json.data.userByName)
  .then(() => {
    fetch(GQL_BASE_PATH, {
      method: "POST",
      headers: {
        "Content-Type": "application/json"
      },
      body: JSON.stringify({
        query: `query SomeQuery {
    gamesListByUserId(userId: "${user.value.id}") {
    game {
      name
    }
    label
  }
}`
      })
    })
      .then(resp => resp.json())
      .then(json => {
        gamesList.value = json.data.gamesListByUserId;
      });
  })


const labels = computed(() => {
  return [...new Set(gamesList.value.map((game) => game.label))];
});

const activeFilterLabel = ref(route.params.filter ? route.params.filter : 'All');
function setActiveFilterLabel(tabName) {
  activeFilterLabel.value = tabName;
}

const listsToShow = computed(() => {
  return activeFilterLabel.value === 'All' ?
    labels.value :
    labels.value.filter((label) => label === activeFilterLabel.value);
})

</script>
<template>
  <div class="container">
    <h3 v-if="user">{{ userName }}</h3>
    <div class="row">
      <div class="col-2">
        <FilterByLabel v-if="labels && activeFilterLabel" :labels="labels" :active-tab="activeFilterLabel" @tab-activated="setActiveFilterLabel" />
      </div>
      <div class="col-10">
        <ListGroup v-for="label in listsToShow" :label="label"
          :entries="gamesList.filter((entry) => entry.label === label)" />
      </div>
    </div>
  </div>
</template>

<style scoped></style>