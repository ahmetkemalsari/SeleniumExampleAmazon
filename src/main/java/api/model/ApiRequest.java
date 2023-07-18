package api.model;

import io.restassured.http.Headers;

import java.util.Map;

public class ApiRequest {

        private String uriPath;
        private String method;
        private String defaultBodyTemplateJson;
        private Map<String, String> headers;

        public String getUriPath() {
                return uriPath;
        }

        public void setUriPath(String uriPath) {
                this.uriPath = uriPath;
        }

        public String getMethod() {
                return method;
        }

        public Map<String, String> getHeaders() {
                return headers;
        }

        public void setHeaders(Map<String, String> headers) {
                this.headers = headers;
        }

        public void setMethod(String method) {
                this.method = method;
        }

        public String getDefaultBodyTemplateJson() {
                return defaultBodyTemplateJson;
        }

        public void setDefaultBodyTemplateJson(String defaultBodyTemplateJson) {
                this.defaultBodyTemplateJson = defaultBodyTemplateJson;
        }


}
