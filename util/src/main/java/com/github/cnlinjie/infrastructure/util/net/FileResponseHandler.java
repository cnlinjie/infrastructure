package com.github.cnlinjie.infrastructure.util.net;

import com.github.cnlinjie.infrastructure.util.spring.StreamUtils;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.impl.client.BasicResponseHandler;

import java.io.InputStream;
import java.io.OutputStream;

public class FileResponseHandler extends BasicResponseHandler {
    private OutputStream outputStream;

    public FileResponseHandler() {

    }

    public FileResponseHandler(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public String handleResponse(HttpResponse httpResponse)
            throws HttpResponseException,
            java.io.IOException {

        StatusLine statusLine = httpResponse.getStatusLine();
        if (statusLine.getStatusCode() == 200) {

            InputStream inputStream = httpResponse.getEntity()
                    .getContent();
            StreamUtils.copy(inputStream, outputStream);
          /*  byte b[] = new byte[1024];
            int j = 0;
			while ((j = inputStream.read(b)) != -1) {
				outputStream.write(b, 0, j);
			}
			outputStream.flush();
*/
        } else {
            return new String(httpResponse.getEntity().toString().getBytes(), "UTF-8");
        }
        return null;
    }
}
