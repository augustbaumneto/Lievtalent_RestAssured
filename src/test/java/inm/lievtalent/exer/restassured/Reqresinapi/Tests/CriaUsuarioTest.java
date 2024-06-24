package inm.lievtalent.exer.restassured.Reqresinapi.Tests;


import static io.restassured.RestAssured.given;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import inm.lievtalent.exer.restassured.Reqresinapi.massatestes.MassaAPICriaUsuario;
import inm.lievtalent.exer.restassured.Reqresinapi.services.json.ReqCreateUserAPI;
import inm.lievtalent.exer.restassured.Reqresinapi.services.json.ResCreateUserAPI;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class CriaUsuarioTest {
    
    private final String BASE_URL = "https://reqres.in/api/users";


    
    @Test
    @DisplayName("Validar criação de um usuário utilizando a API")
    public void validaCriacaoUsuario() {

        ReqCreateUserAPI requisicaousuario = new ReqCreateUserAPI();

        requisicaousuario.setJob(MassaAPICriaUsuario.USUARIO2_CARGO);
        requisicaousuario.setName(MassaAPICriaUsuario.USUARIO2_NOME);

        RequestSpecification requisicao = given();

        requisicao
            .contentType(ContentType.JSON)
            .body(requisicaousuario) 
            .when();

        requisicao.log().all();
        Response resposta = requisicao
                                .post(BASE_URL);

        ValidatableResponse validacao = resposta.then();

        validacao.log().all();

        ResCreateUserAPI respostausuario;

        respostausuario= validacao.extract().as(ResCreateUserAPI.class);

        validacao
                .statusCode(201);
        
        Assertions.assertEquals(MassaAPICriaUsuario.USUARIO2_NOME, respostausuario.getName());
        Assertions.assertEquals(MassaAPICriaUsuario.USUARIO2_CARGO, respostausuario.getJob());
        Assertions.assertNotNull(respostausuario.getCreatedAt());
    }

}