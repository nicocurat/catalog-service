package utils

object EnvironmentProvider {

    def mongoHost: String = sys.env.getOrElse("MONGO_HOST", "0.0.0.0")
    def mongoPort: String = sys.env.getOrElse("MONGO_PORT", "27017")
    def mongoCollection: String = sys.env.getOrElse("MONGO_COLLECTION", "catalogDB")
    def productServiceHost: String = sys.env.getOrElse("PRODUCT_SERVICE_HOST", "catalog-service.default.svc.cluster.local")
    def productServicePort: String = sys.env.getOrElse("PRODUCT_SERVICE_PORT", "8081")

}
