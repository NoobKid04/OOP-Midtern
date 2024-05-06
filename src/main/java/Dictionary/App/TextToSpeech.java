package Dictionary.App;

import javax.speech.Central;
import javax.speech.synthesis.Synthesizer;
import javax.speech.synthesis.SynthesizerModeDesc;
import java.util.Locale;

public class TextToSpeech {

    private static final String VOICES_KEY = "freetts.voices";
    private static final String VOICE_VALUE = "com.sun.speech.freetts.en.us.cmu_us_kal.KevinVoiceDirectory";
    private static final String CENTRAL_DATA = "com.sun.speech.freetts.jsapi.FreeTTSEngineCentral";
    private static Synthesizer sy;
    public static void voice(String data) {
        try {
            System.setProperty(VOICES_KEY,VOICE_VALUE);
            Central.registerEngineCentral( CENTRAL_DATA);

            if(sy==null){
                sy = Central.createSynthesizer(new SynthesizerModeDesc(Locale.US));
                sy.allocate();
                sy.resume();
            }

            sy.speakPlainText(data, null);
            sy.waitEngineState(Synthesizer.QUEUE_EMPTY);
            // sy.deallocate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}