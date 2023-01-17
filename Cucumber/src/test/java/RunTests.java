public class RunTests {
    public static void main(String[] args){

        RunCucumberTest customer_side = new RunCucumberTest();
        RunCucumberTest admin_side = new RunCucumberTest();
        customer_side.addCommentTest();
        admin_side.deleteProductTest();
    }
}
