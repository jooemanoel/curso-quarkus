//----------------------------------------------------------------------------------------------------
export const HttpClient = {
  async get(url) {
    const response = await fetch(url);
    return await response.json();
  },
  async post(url, req) {
    const response = await fetch(url, {
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(req),
    });
    return await response.json();
  },
  async put(url, req) {
    const response = await fetch(url, {
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(req),
    });
    return await response.json();
  },
  async delete(url) {
    const response = await fetch(url, {
      method: "DELETE",
      headers: {
        "Content-Type": "application/json",
      },
    });
    return await response.json();
  },
};
//----------------------------------------------------------------------------------------------------
export function printResponse(res, div) {
  div.innerHTML = JSON.stringify(res, null, 2);
  return res;
}
//----------------------------------------------------------------------------------------------------
