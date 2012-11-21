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
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.joda.time.DateTime;

/**
 * EntityManagerファクトリ
 *
 * @author matsumana
 */
public class EntityManagerFactory {

    @PersistenceContext(unitName = "pu01")
    private EntityManager em01;
    @PersistenceContext(unitName = "pu02")
    private EntityManager em02;

    @RequestScoped
    @Produces
    @EntityManagerQualifier
    public EntityManager getEntityManager() {
        // システム日付が偶数秒ならem01に接続する。
        DateTime dt = new DateTime();
        int sec = Integer.parseInt(dt.toString("s"));
        if (sec % 2 == 0) {
            return em01;
        } else {
            return em02;
        }
    }
}
