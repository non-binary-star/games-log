<script>
export async function postObject(url, object) {
  const resp = await fetch(url, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(object)
  });
  return resp.ok;
}
</script>