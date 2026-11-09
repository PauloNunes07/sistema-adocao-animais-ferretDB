package org.config;

import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.codecs.configuration.CodecRegistries;
import org.bson.codecs.configuration.CodecRegistry;
import org.bson.codecs.pojo.PojoCodecProvider;

public class ConfiguracaoFerretDB {

    private static final String URI =
            "mongodb://ferretuser:FerretDB_2026_Senha@localhost:27017/";

    private static final String nomeBanco = "adocao_animais";

    private static final CodecRegistry CODECS = CodecRegistries.fromRegistries(
                    MongoClientSettings.getDefaultCodecRegistry(),
                    CodecRegistries.fromProviders(PojoCodecProvider.builder()
                                    .automatic(true).build()));

    private static final MongoClient CLIENTE =
            MongoClients.create(
                    MongoClientSettings.builder()
                            .applyConnectionString(
                                    new com.mongodb.ConnectionString(URI)).codecRegistry(CODECS)
                            .build()
            );

    public static MongoDatabase getDatabase() {
        return CLIENTE.getDatabase(nomeBanco);
    }

    public static void fecharConexao() {
        CLIENTE.close();
    }
}
