
import org.junit.Test;

import com.welge.framework.utils.DBUtils;


public class TestClass {
	@Test
	public void printTimestamp(){
		//Data data = new Data();
		System.out.println(System.currentTimeMillis());
		for(int i=0;i<100;i++){
			System.out.println(DBUtils.generateBeanID());
		}
	}
}
