var config = function() {
    let urls = {
        // "schemas": "/api/db/{addr}/{status}",
        "schemas": "/api/db/schemas",
        "tables": "/api/db/tables/{schemaName}",
        "append": "/api/db/append/"
    };

    return {urls: urls};
}();

export default config;