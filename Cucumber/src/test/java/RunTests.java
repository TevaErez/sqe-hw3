public class RunTests {
    public static void main(String[] args){

        RunCucumberTest customer_side = new RunCucumberTest("http://localhost/opencartpro/");
        RunCucumberTest admin_side = new RunCucumberTest("http://localhost/opencartpro/admin/index.php?route=catalog/product");
        customer_side.addCommentTest();
        admin_side.deleteProductTest();
    }
}
