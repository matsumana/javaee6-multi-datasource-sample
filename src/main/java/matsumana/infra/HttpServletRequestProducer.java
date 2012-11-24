/*
 * Copyright 2012 matsumana
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package matsumana.infra;

import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

/**
 * HttpServletRequestをDI可能にするためのプロデューサー
 *
 * @author matsumana
 */
@WebListener
public class HttpServletRequestProducer implements ServletRequestListener {

    private final static ThreadLocal<HttpServletRequest> holder =
            new ThreadLocal();

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {
        holder.remove();
    }

    @Override
    public void requestInitialized(ServletRequestEvent sre) {
        holder.set((HttpServletRequest) sre.getServletRequest());
    }

    @Produces
    @RequestScoped
    HttpServletRequest get() {
        return holder.get();
    }
}
