// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import frc.robot.Constants.OperatorConstants;
import frc.robot.Constants.ShooterConstants.FeederState;
import frc.robot.Constants.ShooterConstants.ShooterState;
import frc.robot.commands.Autos;
import frc.robot.commands.Drive;
import frc.robot.commands.ExampleCommand;
import frc.robot.commands.SetFeederState;
import frc.robot.commands.SetShooterState;
import frc.robot.lib.FluentTrigger;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.ExampleSubsystem;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Shooter;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.CommandXboxController;
import edu.wpi.first.wpilibj2.command.button.Trigger;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and trigger mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final CommandXboxController primaryController = new CommandXboxController(Constants.OperatorConstants.kDriverControllerPort);
  private final Drivetrain drivetrain = new Drivetrain();
  private final Drive drive = new Drive(drivetrain, primaryController);
  private final Shooter shooter = new Shooter();
  private final Feeder feeder = new Feeder();

  // Replace with CommandPS4Controller or CommandJoystick if needed
  private final CommandXboxController m_driverController = new CommandXboxController(
      OperatorConstants.kDriverControllerPort);

  /**
   * The container for the robot. Contains subsystems, OI devices, and commands.
   */
  public RobotContainer() {
    // Configure the trigger bindings
    configureBindings();
    drivetrain.setDefaultCommand(drive);
  }

  /**
   * Use this method to define your trigger->command mappings. Triggers can be
   * created via the
   * {@link Trigger#Trigger(java.util.function.BooleanSupplier)} constructor with
   * an arbitrary
   * predicate, or via the named factories in {@link
   * edu.wpi.first.wpilibj2.co
   * mmand.button.CommandGenericHID}'s subclasses for {@link
   * CommandXboxController
   * Xbox}/{@link edu.wpi.first.wpilibj2.command.button.CommandPS4Controller
   * PS4} controllers or
   * {@link edu.wpi.first.wpilibj2.command.button.CommandJoystick Flight
   * joysticks}.
   */
  private void configureBindings() {
    // Schedule `ExampleCommand` when `exampleCondition` changes to `true`
    new Trigger(m_exampleSubsystem::exampleCondition)
        .onTrue(new ExampleCommand(m_exampleSubsystem));

    // Schedule `exampleMethodCommand` when the Xbox controller's B button is
    // pressed,
    // cancelling on release.
    m_driverController.b().whileTrue(m_exampleSubsystem.exampleMethodCommand());

    new FluentTrigger(
        new SetShooterState(shooter, ShooterState.IDLE),
        new FluentTrigger.CommandBind(primaryController.button(OperatorConstants.kGamepadRightBumper), new SetShooterState(shooter, ShooterState.SHOOT)),
        new FluentTrigger.CommandBind(primaryController.button(OperatorConstants.kGamepadLeftTrigger), new SetShooterState(shooter, ShooterState.INTAKE))
    );
    new FluentTrigger(
        new SetFeederState(feeder, FeederState.IDLE),
        new FluentTrigger.CommandBind(primaryController.button(OperatorConstants.kGamepadLeftBumper), new SetFeederState(feeder, FeederState.SHOOT)),
        new FluentTrigger.CommandBind(primaryController.button(OperatorConstants.kGamepadLeftTrigger), new SetFeederState(feeder, FeederState.INTAKE))
    );
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An example command will be run in autonomous
    return Autos.exampleAuto(m_exampleSubsystem);
  }
}
