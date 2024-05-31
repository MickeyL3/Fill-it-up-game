package seng201.team0.gui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import seng201.team0.models.Cart;
import seng201.team0.services.RandomEventService;
import seng201.team0.services.EnvironmentManager;


public class RoundDifficultySelectController {

    private EnvironmentManager environmentManager;
    private RandomEventService randomEventService;
    private Cart cart;

    @FXML
    private RadioButton easyRadioButton;

    @FXML
    private RadioButton moderateRadioButton;

    @FXML
    private RadioButton challengingRadioButton;

    @FXML
    private Button playNowButton;

    @FXML
    private Label currentRoundLabel;
    @FXML
    private Label randomEventAlert;

    @FXML
    private void onExitClicked() {
        System.exit(0);
    }
    public RoundDifficultySelectController(EnvironmentManager environmentManager, RandomEventService randomEventService) {
        this.environmentManager = environmentManager;
        this.randomEventService = randomEventService;
        if(randomEventService.isHasRandomEvent()) {
            System.out.println("Next round will have an undesirable event happens, be smart to manage your current tower list. Good luck!");
        }
    }

    /**
     * Initialize round difficulty select screen
     */
    public void initialize(){
        currentRoundLabel.setText(String.valueOf(environmentManager.getCurrentRoundNumber()));
    }
    @FXML
    public void onOpenInventoryButtonClicked() {
        environmentManager.closeCurrentScreen();
        environmentManager.launchInventoryScreen();
    }

    public void onBackButtonClicked() {
        environmentManager.closeCurrentScreen();
        environmentManager.launchSetupScreen();
    }

    public void onEasyRadioButtonClicked() {
        easyRadioButton.setSelected(true);
        environmentManager.setRoundDifficulty("Easy");
        moderateRadioButton.setSelected(false);
        challengingRadioButton.setSelected(false);
        playNowButton.setDisable(false);

    }
    public void onModerateRadioButtonClicked() {
        moderateRadioButton.setSelected(true);
        environmentManager.setRoundDifficulty("Moderate");
        easyRadioButton.setSelected(false);
        challengingRadioButton.setSelected(false);
        playNowButton.setDisable(false);

    }
    public void onChallengingRadioButtonClicked() {
        challengingRadioButton.setSelected(true);
        environmentManager.setRoundDifficulty("Challenging");
        easyRadioButton.setSelected(false);
        moderateRadioButton.setSelected(false);
        playNowButton.setDisable(false);

    }

    public void onPlayNowButtonClicked() {
        if (environmentManager.getGameDifficulty().equals("Easy")){
            environmentManager.closeCurrentScreen();
            environmentManager.launchEasyGameScreen();
        }
        if (environmentManager.getGameDifficulty().equals("Moderate")) {
            environmentManager.closeCurrentScreen();
            environmentManager.launchModerateGameScreen();

        }
        if (environmentManager.getGameDifficulty().equals("Challenging")){
            environmentManager.closeCurrentScreen();
            environmentManager.launchChallengingGameScreen();
        }

    }

}
