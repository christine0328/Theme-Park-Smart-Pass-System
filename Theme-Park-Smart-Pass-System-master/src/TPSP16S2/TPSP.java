package TPSP16S2;

public class TPSP {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		if (args.length == 4) {
			Processor op = new Processor(args);
			op.readcardsFile();
			
			op.readInstruction();
			op.saveResult();

		}
	}

}
