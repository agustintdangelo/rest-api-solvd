import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RestApi {

    @DataProvider(name = "data-provider")
    public Object[][] dataProviderMethod() {
        return new Object[][]{{"dollar", "bbva"}, {"euro", "nacion"},{"dollar", "mayorista"},{"real","chaco"}};
    }


    @Test(dataProvider = "data-provider")
    public void getDollar(String moneyType, String bank) {
        RestAssured.baseURI = "https://api-dolar-argentina.herokuapp.com/api";
//      String body = RestAssured.given().when().get("/dolaroficial").body().asString();
        String buy, sell;

        Object pathParams = bank;

        if (moneyType == "dollar") {
            buy = RestAssured.given()
                    .pathParams("bank", bank)
                    .when()
                    .get("/{bank}")
                    .path("compra");
            sell = RestAssured.given()
                    .pathParams("bank", bank)
                    .when()
                    .get("/{bank}")
                    .path("venta");
            System.out.println("dollar price in " + bank + ". Buy: " + buy + " Sell: " + sell);
        } else if (moneyType == "euro" || moneyType == "real") {
            buy = RestAssured.given()
                    .pathParams("bank", bank)
                    .pathParams("moneyType", moneyType)
                    .when().get("/{moneyType}/{bank}")
                    .path("compra");
            sell = RestAssured.given()
                    .pathParams("bank", bank)
                    .pathParams("moneyType", moneyType)
                    .when().get("/{moneyType}/{bank}")
                    .path("venta");
            System.out.println(moneyType + " price in " + bank + ". Buy: " + buy + " Sell: " + sell);
        }
    }


}
