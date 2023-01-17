public class RunTests {
    public static void main(String[] args){

        RunCucumberTest customer_side = new RunCucumberTest("http://localhost/opencartpro/");
        customer_side.addCommentTest();
        RunCucumberTest admin_side = new RunCucumberTest("http://localhost/opencartpro/admin/index.php?route=catalog/product");
        admin_side.deleteProductTest();
        customer_side.submitComment();

    }
}
