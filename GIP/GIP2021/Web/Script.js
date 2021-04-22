// JavaScript source code

new Vue ({
    el: '#app',
    data() {
        return {
            urls: [],
            searchString: "",
            selectedSearch:""
        }
    },
    mounted() {
            axios.get("http://localhost:91/insert/fromPaths")
            axios.get("http://localhost:91/delete/tag/noLongerConnectedToImage")
            axios.get("http://localhost:91/delete/Image/NoLongerInFolder")
    },
    methods: {
        openInedpth: function (filepath) {
            console.log("test")
            sessionStorage.setItem("url", filepath)
            sessionStorage.setItem('LastPage', "index.html")
            open("indepth.html")
            window.close()
        },
        openAuthors: function(){
            open("authors.html")
            sessionStorage.setItem('LastPage', "authors.html")
            window.close()
        }
    },
    computed:{
        Search: function(){
            if(this.searchString == "" || this.selectedSearch == ""){
                axios
                .get("http://localhost:91/get/URL")
                .then(response => (this.urls = response.data))
                return this.urls
            }
            if(this.selectedSearch == "Tag"){
                axios.get("http://localhost:91/get/bytag?s=" + this.searchString)
                .then(response => (this.urls = response.data))
                return this.urls
            }
            if(this.selectedSearch == "Author"){
                axios
                .get("http://localhost:91/get/byAuthor?s=" + this.searchString)
                .then(response => (this.urls = response.data))
                return this.urls
            }
        }
    }

})

