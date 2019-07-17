package com.polohach.weather.network

import android.content.Context
import com.polohach.weather.utils.printLog
import com.polohach.weather.utils.printLogE
import okhttp3.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.URI
import java.util.*

class MockInterceptor(val context: Context) : Interceptor {

    companion object {
        private const val FILE_EXTENSION = ".json"
        private const val READ_DATA_FROM_FILE = "Read data from file:  %s"
        private const val RESPONSE_TAG = "Response:  %s"
        private const val REQUEST_URL = "--> Request url: [%s]  %s"
        private const val FILE_NOT_EXIST = "File not exist:  %s"
        private const val END_REQUEST = "<-- END [%s]  %s"
        private const val SCAN_FILE = "Scan files in: %s"
        private const val URI_DIVIDER = '/'
        private const val DEFAULT_FILE_NAME = "index$FILE_EXTENSION"

        private const val STATUS_CODE_OK = 200
    }

    private var contentType = APPLICATION_JSON

    /**
     * Set content type for header
     *
     * @param contentType Content type
     * @return JavaMocksInterceptor
     */
    fun setContentType(contentType: String) =
            this.apply { this.contentType = contentType }

    override fun intercept(chain: Interceptor.Chain): Response? {
        var response: Response? = null

        val listSuggestionFileName = ArrayList<String>()
        val method = chain.request().method().toLowerCase()

        // Get Request URI.
        val uri = chain.request().url().uri()
        String.format(REQUEST_URL, method.toUpperCase(), uri).printLog()
        val defaultFileName = getFileName(chain)

        //create file name with http method
        //eg: getLogin.json
        listSuggestionFileName.add(method.plus(upCaseFirstLetter(defaultFileName)))

        //eg: login.json
        listSuggestionFileName.add(defaultFileName)

        getFirstFileNameExist(listSuggestionFileName, uri)
                ?.let { fileName ->
                    val filePath = getFilePath(uri, fileName)
                    String.format(READ_DATA_FROM_FILE, filePath).printLog()
                    try {
                        val inputStream = context.assets.open(filePath)
                        val r = BufferedReader(InputStreamReader(inputStream))
                        val responseStringBuilder = StringBuilder()
                        var line: String?
                        while ((r.readLine().apply { line = this }) != null) {
                            responseStringBuilder.append(line).append('\n')
                        }
                        String.format(RESPONSE_TAG, responseStringBuilder).printLog()
                        response = Response.Builder()
                                .code(STATUS_CODE_OK)
                                .message(responseStringBuilder.toString())
                                .request(chain.request())
                                .protocol(Protocol.HTTP_1_0)
                                .body(ResponseBody.create(MediaType.parse(contentType),
                                        responseStringBuilder.toString().toByteArray()))
                                .addHeader(CONTENT_TYPE, contentType)
                                .build()
                    } catch (e: IOException) {
                        e.printLogE()
                    }
                }
                ?: let {
                    for (file in listSuggestionFileName) {
                        String.format(FILE_NOT_EXIST, getFilePath(uri, file)).printLog()
                    }
                    response = chain.proceed(chain.request())
                }

        String.format(END_REQUEST, method.toUpperCase(), uri).printLog()
        return response
    }

    private fun upCaseFirstLetter(str: String) =
            str.substring(0, 1)
                    .toUpperCase()
                    .plus(str.substring(1))

    private fun getFileName(chain: Interceptor.Chain) =
            chain.request()
                    .url()
                    .pathSegments()
                    .run { get(lastIndex) }
                    .run { if (isEmpty()) DEFAULT_FILE_NAME else this.plus(FILE_EXTENSION) }

    private fun getFilePath(uri: URI, fileName: String?) = uri.path.run {
        uri.host
                .plus(if (lastIndexOf(URI_DIVIDER) != lastIndex) substring(0, lastIndexOf(URI_DIVIDER) + 1) else this)
                .plus(fileName)
    }

    @Throws(IOException::class)
    private fun getFirstFileNameExist(inputFileNames: List<String>, uri: URI): String? {
        val mockDataPath = uri.run {
            host.plus(path).run { substring(0, lastIndexOf(URI_DIVIDER)) }
        }
        String.format(SCAN_FILE, mockDataPath).printLog()
        val files = context.assets.list(mockDataPath) //List all files in folder
        for (fileName in inputFileNames) {
            for (file in files) {
                if (fileName == file) return fileName
            }
        }
        return null
    }
}
