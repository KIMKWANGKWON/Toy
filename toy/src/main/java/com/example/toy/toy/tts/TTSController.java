package com.example.toy.toy.tts;

import com.google.cloud.texttospeech.v1.*;
import com.google.protobuf.ByteString;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Base64;

@Controller
public class TTSController {
    @GetMapping("/tts")
    public String tts() {
        return "tts";
    }
    @PostMapping("/tts")
    @ResponseBody
    public String toTTS(@RequestParam String text) throws Exception {
        String audio;
        // Instantiates a client
        try (TextToSpeechClient textToSpeechClient = TextToSpeechClient.create()) {
            // Set the text input to be synthesized
            SynthesisInput input = SynthesisInput.newBuilder().setText(text).build();

            // Build the voice request, select the language code ("en-US") and the ssml voice gender
            // ("neutral")
            VoiceSelectionParams voice =
                    VoiceSelectionParams.newBuilder()
                            .setLanguageCode("ko-KR")
                            .setSsmlGender(SsmlVoiceGender.NEUTRAL)
                            .setName("ko-KR-Wavenet-C")
                            .build();

            // Select the type of audio file you want returned
            AudioConfig audioConfig =
                    AudioConfig.newBuilder().setAudioEncoding(AudioEncoding.MP3).build();

            // Perform the text-to-speech request on the text input with the selected voice parameters and
            // audio file type
            SynthesizeSpeechResponse response =
                    textToSpeechClient.synthesizeSpeech(input, voice, audioConfig);

            // Get the audio contents from the response
            ByteString audioContents = response.getAudioContent();
            // Write the response to the output file.
            try (OutputStream out = new FileOutputStream("output.mp3")) {
                out.write(audioContents.toByteArray());
                audio = Base64.getEncoder().encodeToString(audioContents.toByteArray());
                System.out.println("Audio content written to file \"output.mp3\"");
            }
        }
        System.out.println(audio);
        return audio;
    }
}
