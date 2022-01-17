
public class Answers {
	
			//stage 1 given letters
			static String answers_stage1[][] = {{"ΟΜΗΡΟΣ", "ΤΗΛΕΜΑΧΟ", "ΠΗΝΕΛΟΠΗ", "ΜΕΣΟΓΕΙΟ", "ΙΘΑΚΗ"},
												{"ΜΕΣΣΗΝΗΣ","ΣΤΙΧΟΥΣ","ΣΕΙΡΗΝΕΣ","ΑΣΚΙ","ΚΕΡΙ"},
												{"ΠΟΛΥΦΗΜΟ","ΚΙΡΚΗ","ΚΛΑΔΙ","ΚΑΝΕΝΑΣ","ΧΟΙΡΟΥΣ"},
												{"ΧΑΡΥΒΔΗ","ΕΠΤΑ","ΚΑΛΥΨΩ","ΑΘΗΝΑ","ΔΙΑΣ"},
												{"ΖΗΤΙΑΝΟ","ΙΛΙΑΔΑΣ","ΕΝΑ","ΕΙΚΟΣΙ","ΔΟΥΡΕΙΟΣ"}};


			//stage 2 given letters
			static String answers_stage2[][]= {{"ΔΩΔΕΚΑ","ΖΩΝΗ","ΦΗΜΗ","ΔΙΑ","ΙΦΙΚΛΗΣ"},
												{"ΘΗΒΑ","ΗΡΑ","ΦΙΔΙΑ","ΞΑΔΕΛΦΟΣ","ΠΥΘΙΑ"},
												{"ΝΕΜΕΑΣ","ΚΟΡΙΝΘΟ","ΑΘΑΝΑΤΟ","ΗΡΑ","ΕΛΑΦΙΟΥ"},
												{"ΥΔΡΑ","ΕΝΝΕΑ","ΤΙΤΑΝΑΣ","ΔΡΕΠΑΝΙ","ΓΟΥΡΟΥΝΙ"},
												{"ΚΕΡΒΕΡΟ","ΔΡΑΚΟΣ","ΑΙΜΑ","ΑΤΛΑΣ","ΗΡΑ"}};


			//stage 3 given letters
			static String answers_stage3[][] = {{"ΗΦΑΙΣΤΟΥ","ΑΦΡΟΔΙΤΗ",  "ΦΤΕΡΝΑ", "ΞΥΛΙΝΟΣ", "ΛΩΤΟ"},
												{"ΚΝΩΣΟΥ", "ΜΙΝΩΑΣ","ΦΤΕΡΑ",  "ΕΡΜΗΣ", "ΕΞΙ"},
												{ "ΕΦΤΑ", "ΘΗΣΕΑΣ","ΙΚΑΡΟΣ", "ΝΕΚΤΑΡ", "ΕΝΝΕΑ"},
												{"ΑΣΠΡΟ", "ΤΡΙΑΙΝΑ", "ΑΦΡΟ", "ΜΟΥΣΕΣ", "ΓΟΡΓΟΝΑ"},
												{"ΤΡΩΙΚΟ", "ΚΛΩΣΤΗ", "ΜΑΥΡΑ", "ΗΛΙΟ", "ΘΗΣΕΑΣ"}};


			//stage 4 given letters
			static String answers_stage4[][] = {{"ΔΕΚΑ", "ΘΕΤΙΔΑ", "ΑΡΤΕΜΙΣ", "ΑΔΗΣ", "ΓΡΑΙΕΣ"},
												{"ΟΔΥΣΣΕΑ", "ΗΜΙΘΕΟΣ", "ΠΕΤΡΑ", "ΚΛΕΙΩ", "ΠΑΡΗΣ"},
												{"ΦΙΔΙΑ", "ΠΑΝΔΩΡΑ", "ΣΑΡΑΝΤΑ", "ΠΟΤΑΜΩΝ", "ΑΜΒΡΟΣΙΑ"},
												{"ΜΕΝΕΛΑΟΥ","ΣΦΙΓΓΑ", "ΕΡΙΔΑΣ",  "ΚΡΑΣΙ", "ΑΝΑΚΤΟΡΑ"},
												{"ΔΥΟ", "ΕΚΤΟΡΑΣ", "ΑΔΗ", "ΠΗΛΕΑ", "ΠΕΝΗΝΤΑ"}};


			//stage 5 given letters
			static String answers_stage5[][] = {{"ΓΑΙΑ", "ΝΑΞΟ", "ΣΤΑΒΛΟΥΣ", "ΧΕΙΡΩΝΑ", "ΠΕΡΣΕΑΣ"},
												{"ΖΩΗ", "ΤΡΕΙΣ", "ΑΙΗΤΗ", "ΙΩΛΚΟ", "ΦΩΤΙΑ"},
												{ "ΟΡΦΕΑΣ", "ΠΕΡΙΣΤΕΡΙ","ΕΛΠΙΔΑ", "ΔΕΚΑ", "ΑΤΑΛΑΝΤΗ"},
												{"ΦΙΝΕΑΣ", "ΑΡΗ", "ΠΟΣΕΙΔΩΝΑ", "ΕΛΕΝΗΣ", "ΣΥΚΩΤΙ"},
												{"ΗΡΑΚΛΗ", "ΑΡΕΤΗΣ", "ΑΘΗΝΑΣ", "ΤΡΑΓΟΥ", "ΑΝΘΡΩΠΟΣ"}};
		
		/*This method return the answer of the given level and round according to which of the 5 possible
		 * array of questions we got from the random variable in the boardPanel class
		 */
		public static String getAnswer(int i, int j, int q) {
			if (q == 0) {
				return answers_stage1[i][j];
			} else if (q == 1) {
				return answers_stage2[i][j];
			} else if (q == 2) {
				return answers_stage3[i][j];
			} else if (q == 3) {
				return answers_stage4[i][j];
			} else {
				return answers_stage5[i][j];
			}
		}

}
