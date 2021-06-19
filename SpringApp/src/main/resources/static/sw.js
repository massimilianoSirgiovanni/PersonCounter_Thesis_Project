self.addEventListener("install", e =>{
    e.waitUntil(
    caches.open("static").then(cache => {
        return cache.addAll(["./", "./css/style.css", "./images/logo192.png", "./images/logo512.png", "./images/addStore.png", "./images/getStore.png", "./images/getAllStores.png", "./images/removeStore.png", "./images/setMax.png", "./images/deleteMessages.png", "./images/logo.png", "./images/Messages.png", "./images/messagesSelectedByStore.png" ]);
    })
    );
});

self.addEventListener("fetch", e => {
    e.respondWith(
    caches.match(e.request).then(response => {
        return response || fetch(e.request);
    })
        );
});