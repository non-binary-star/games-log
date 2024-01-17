<script setup>
import FilterTab from "./FilterTab.vue";
import { computed, reactive, ref } from "vue";

const props = defineProps(['labels', 'activeTab']);
const emit = defineEmits(['tabActivated']);

const combinedTabs = computed(() => {
  const temp = props.labels.map((label) => {
    return reactive({
      name: label,
      isActive: false
    })
  });
  temp.unshift(reactive({
    name: 'All',
    isActive: false
  }));
  temp.find((tab) => tab.name === props.activeTab).isActive = true;
  return temp;
})

function getActiveTab() {
  return combinedTabs.value.find((t) => t.isActive);
}

function toggle(from, to) {
  if (from !== to) {
    from.isActive = false;
    to.isActive = true;
    emit('tabActivated', to.name);
  }
}
</script>
<template>
  <div class="list-group">
    <FilterTab v-for="tab in combinedTabs" :key="tab.name" :name="tab.name" :is-active="tab.isActive"
      @click="toggle(getActiveTab(), tab)" />
  </div>
</template>