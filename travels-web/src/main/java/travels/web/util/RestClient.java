package travels.web.util;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Created by irwin on 12/3/14.
 */
public class RestClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestClient.class);
    public static final String HTTP_PROTOCOL = "http://";
    public static final String UTF_8 = "utf-8";

    private final HttpClient httpClient;

    public RestClient(HttpClient httpClient) {
        this.httpClient = httpClient;
    }

    /**
     * Method to invoke HTTP GET method.
     * @param url url to be invoked
     * @param nullValue return value if the result is null or caught by exception
     * @return String response from HTTP invoke
     * @throws java.io.IOException
     */
    public String get(String url, String nullValue, Map<String, String> httpHeader) throws IOException {

        String result;

        HttpGet httpGet = new HttpGet(url);
        setHeader(httpGet, httpHeader);
        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        result = httpClient.execute(httpGet, responseHandler);

        LOGGER.trace("GET Result from URL {} = {}", url, result);
        return result;
    }

    /**
     * Method to invoke HTTP POST method.
     * @param url url to be invoked
     * @param nullValue return value if the result is null or caught by exception
     * @param httpParameter parameter supplied to HTTP invoke
     * @return String response from HTTP invoke
     * @throws java.io.IOException
     */
    public String post(String url, String nullValue, Map<String, String> httpParameter, Map<String, String> httpHeader)
            throws IOException {

        String result = nullValue;

        HttpPost httpPost = new HttpPost(url);
        setHeader(httpPost, httpHeader);

        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        List<NameValuePair> nameValuePairList = createNameValuePair(httpParameter);

        httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairList, UTF_8));
        result = httpClient.execute(httpPost, responseHandler);

        LOGGER.trace("POST Result from URL {} = {}", url, result);
        return result;
    }

    /**
     * Method to invoke HTTP POST method.
     * @param url url to be invoked
     * @param nullValue return value if the result is null or caught by exception
     * @param httpBody string to be placed on HTTP body
     * @param connectionTimeout timeout value in millisecond, 0 mean no timeout
     * @param socketTimeout timeout value in millisecond, 0 mean no timeout
     * @return String response from HTTP invoke
     * @throws java.io.IOException
     */
    public String post(String url, String nullValue, String httpBody, Map<String, String> httpHeader,
                       int connectionTimeout, int socketTimeout)
            throws IOException {

        String result = nullValue;

        HttpPost httpPost = new HttpPost(url);
        setHeader(httpPost, httpHeader);

        ResponseHandler<String> responseHandler = new BasicResponseHandler();

        httpPost.setEntity(new StringEntity(httpBody));
        result = httpClient.execute(httpPost, responseHandler);

        LOGGER.trace("POST Result from URL {} = {}", url, result);
        return result;
    }

    /**
     * Method to invoke HTTP PUT method.
     * @param url url to be invoked
     * @param nullValue return value if the result is null or caught by exception
     * @param httpParameter parameter supplied to HTTP invoke
     * @return String response from HTTP invoke
     * @throws java.io.IOException
     */
    public String put(String url, String nullValue, Map<String, String> httpParameter, Map<String, String> httpHeader)
            throws IOException {

        String result = nullValue;

        HttpPut httpPut = new HttpPut(url);
        setHeader(httpPut, httpHeader);

        ResponseHandler<String> responseHandler = new BasicResponseHandler();
        List<NameValuePair> nameValuePairList = createNameValuePair(httpParameter);

        httpPut.setEntity(new UrlEncodedFormEntity(nameValuePairList, UTF_8));
        result = httpClient.execute(httpPut, responseHandler);

        LOGGER.trace("PUT Result from URL {} = {}", url, result);
        return result;
    }

    /**
     * Method to invoke HTTP PUT method.
     * @param url url to be invoked
     * @param nullValue return value if the result is null or caught by exception
     * @param httpBody string to be placed on HTTP body
     * @return String response from HTTP invoke
     * @throws java.io.IOException
     */
    public String put(String url, String nullValue, String httpBody, Map<String, String> httpHeader)
            throws IOException {

        String result;

        HttpPut httpPut = new HttpPut(url);
        setHeader(httpPut, httpHeader);

        ResponseHandler<String> responseHandler = new BasicResponseHandler();

        httpPut.setEntity(new StringEntity(httpBody));
        result = httpClient.execute(httpPut, responseHandler);

        LOGGER.trace("POST Result from URL {} = {}", url, result);
        return result;
    }

    /**
     * Method to invoke HTTP DELETE method.
     * @param url url to be invoked
     * @param nullValue return value if the result is null or caught by exception
     * @return String response from HTTP invoke
     * @throws java.io.IOException
     */
    public String delete(String url, String nullValue, Map<String, String> httpHeader) throws IOException {

        String result;

        HttpDelete httpDelete = new HttpDelete(url);
        setHeader(httpDelete, httpHeader);
        ResponseHandler<String> responseHandler = new BasicResponseHandler();

        result = httpClient.execute(httpDelete, responseHandler);

        LOGGER.trace("DELETE Result from URL {} = {}", url, result);
        return result;
    }

    /**
     * Construct HTTP URL from separate string, for example http://localhost:8080/app/mypath
     * localhost is the host, 8080 will is th port, hbm.app is the context and mypath is the serlvetPath
     * @param host host destination
     * @param port port destination
     * @param context context destination
     * @param servletPath serlvetPath destination
     * @return qualified HTTP URL
     */
    public String buildHttpUrl(String host, int port, String context, String servletPath) {
        return HTTP_PROTOCOL + host + ':' + port + '/' + context + '/' + servletPath;
    }

    private List<NameValuePair> createNameValuePair(Map<String, String> httpParameter) {
        List<NameValuePair> nameValuePairList = new LinkedList<NameValuePair>();
        if (httpParameter != null) {
            for (Map.Entry<String, String> entry : httpParameter.entrySet()) {
                nameValuePairList.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
            }
        }
        return nameValuePairList;
    }

    private void setHeader(HttpRequestBase httpRequestBase, Map<String, String> httpHeader) {
        if (httpHeader != null) {
            for (Map.Entry<String, String> entry : httpHeader.entrySet()) {
                httpRequestBase.setHeader(entry.getKey(), entry.getValue());
            }
        }
    }

    public InputStream download(String url, Map<String, String> httpHeader) throws IOException {
        HttpGet httpGet = new HttpGet(url);
        setHeader(httpGet, httpHeader);

        BufferedInputStream bufferInput = null;
        bufferInput = new BufferedInputStream(httpClient.execute(httpGet).getEntity().getContent());
        return bufferInput;
    }

}
