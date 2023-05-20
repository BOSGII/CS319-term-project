import { useState, useEffect } from "react";

export const useFetch = (url, method = "GET") => {
  const [data, setData] = useState(null);
  const [isPending, setIsPending] = useState(false);
  const [error, setError] = useState(null);
  const [options, setOptions] = useState(null);
  const [refresh, setRefresh] = useState(false);
  const [urlState, setUrlState] = useState(url);

  const refreshList = async () => {
    await new Promise((r) => setTimeout(r, 100));
    setRefresh(!refresh);
  };

  const postData = (postData) => {
    setOptions({
      method: "POST",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(postData),
    });
  };

  const putData = (pathVariable, putData) => {
    setUrlState(url.concat(`/${pathVariable}`));
    setOptions({
      method: "PUT",
      headers: {
        "Content-Type": "application/json",
      },
      body: JSON.stringify(putData),
    });
  };

  const deleteData = (pathVariable) => {
    setUrlState(url.concat(`/${pathVariable}`));
    setOptions({
      method: "DELETE",
    });
  };

  useEffect(() => {
    const controller = new AbortController();

    const fetchData = async (fetchOptions) => {
      setIsPending(true);

      try {
        const res = await fetch(urlState, {
          ...fetchOptions,
          signal: controller.signal,
        });
        if (!res.ok) {
          throw new Error(res.statusText);
        }

        const data = await res.json();
        console.log(data.maxNumOfInternships);

        setIsPending(false);
        setData(data);
        setError(null);
      } catch (err) {
        if (err.name === "AbortError") {
          console.log("the fetch was aborted");
        } else {
          setIsPending(false);
          setError("Could not fetch the data");
        }
      }
    };

    // invoke the function
    if (method === "GET") {
      fetchData();
    }
    if (
      (method === "POST" || method === "PUT" || method === "DELETE") &&
      options
    ) {
      fetchData(options);
    }

    return () => {
      controller.abort();
    };
  }, [urlState, method, options, refresh]);

  return { data, isPending, error, postData, putData, deleteData, refreshList };
};
