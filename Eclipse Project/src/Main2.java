import java.io.StringReader;
import java.util.Iterator;
import java.util.List;

import edu.stanford.nlp.ling.HasWord;
import edu.stanford.nlp.parser.common.ParserQuery;
import edu.stanford.nlp.parser.lexparser.LexicalizedParser;
import edu.stanford.nlp.process.Tokenizer;
import edu.stanford.nlp.trees.Tree;
import edu.stanford.nlp.trees.TreebankLanguagePack;
import edu.stanford.nlp.util.ScoredObject;

public class Main2 {

	public static void main(String[] args) {
				
				//Verifica funzionamento parser stanford
				
		String reqs[] = {"After {Power-up} becomes true, {Tapproaching} shall {Req1} be set to false.",
			"After {Power-up becomes true, {Tin} shall {Req2} be set to false.",
			"After {Power-up becomes true, {Tleave} shall {Req3} be set to false.",
			"After {Power-up} becomes true, {Gopen} shall {Req4} be set to true.",
			"When {Tdistance} is less than 3000, {Tapproaching} shall {Req5} be set to true.",
			"If {Tapproaching} is true, then {Gopen} shall {Req6} be set to false.",
			"If {Tdistance} is less than 1000, {Tin} shall {Req7} be set to true.",
			"After {Tin} becomes true, when {Tdistance} is greater than 3000, {Tleave} shall {Req8} be set to true.",
			"If {Tleave} is true, then {Gopen} shall {Req9} be set to true.",
			"When {Tdistance} is greater than 10000, {Tleave} shall {Req10} be set to false.",
			"When {Tdistance} is greater than 10000, {Tin} shall {Req11} be set to false.",
			"When {Tdistance} is greater than 10000, {Tapproaching} shall {Req12} be set to false.",
			"After {Power-up} becomes true until {Tdistance} is greater than 10000, {Gopen} shall {Req4} be set to true."};
				
				
			String parserModel = "englishPCFG.ser.gz";
			LexicalizedParser lp = LexicalizedParser.loadModel(parserModel);
			TreebankLanguagePack tlp = lp.getOp().langpack();
			for(int i = 0; i < reqs.length; i++){
					
				Tokenizer<? extends HasWord> toke =
				        tlp.getTokenizerFactory().getTokenizer(new StringReader(reqs[i]));
					
				List<? extends HasWord> mySentence = toke.tokenize();
				ParserQuery pq = lp.parserQuery();
				pq.parse(mySentence);
				List<ScoredObject<Tree>> treeList = pq.getKBestParses(1);
				for (ScoredObject<Tree> scoredObject : treeList) {
					System.out.println("");
					System.out.println("");
					System.out.println("");
					System.out.println("Req" + i + ":");
					scoredObject.object().pennPrint();
					System.out.println(scoredObject.score());
						
					System.out.println("");
					System.out.println("Single Tree Print:");
					Tree myTree = scoredObject.object();
					List<Tree> leaves = myTree.getLeaves();
					Iterator<Tree> iterLeaves = leaves.iterator();
					while(iterLeaves.hasNext()){
						Tree auxTree = iterLeaves.next();
						auxTree.ancestor(1, myTree).pennPrint();
					}
				}
			}
			
		}

	}
