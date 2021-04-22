package com.github.Dagnija

import scala.collection.mutable
import scala.io.StdIn.readLine

object TicTacToe extends App {

  class GameState(var playground: mutable.SortedSet[Int] = collection.mutable.SortedSet(1, 2, 3, 4, 5, 6, 7, 8, 9), //total playground size 9
                  val minMove: Int = 1,
                  val maxMove: Int = 9,
                  var isPlayerXTurn: Boolean = true, //two players X and O, X starts the game
                  var playerXMoves: mutable.Set[Int] = collection.mutable.Set[Int](),
                  var playerOMoves: mutable.Set[Int] = collection.mutable.Set[Int](),
                  val winningSets: Seq[Set[Int]] = Seq(Set(1, 2, 3), Set(4, 5, 6), Set(7, 8, 9), Set(1, 4, 7), Set(2, 5, 8), Set(3, 6, 9), Set(1, 5, 9), Set(3, 5, 7))
                 )

  val state = new GameState()
  //  def init():GameState = {

  println("Let's play Tic-tac-toe!")

  val startBoard: Array[Char] = ('1' to '9').toArray
  print(
    0 to 2 map { r =>
      0 to 2 map { c =>
        startBoard(c + r * 3)
      } mkString " | "
    } mkString("_________\n", "\n---------\n", "\n"))

  val playerX = readLine(s"What is your name Player X?").trim.toString
  val playerO = readLine(s"What is your name Player O?").trim.toString

  def getPlayerTurn(isPlayerXTurn: Boolean): String = if (isPlayerXTurn) playerX else playerO

  def containsSubset(playerMoves: mutable.Set[Int]): Boolean = {
    for (winningSet <- state.winningSets) {
      if (winningSet.subsetOf(playerMoves)) {
        return true
      }
    }
    false
  }

  while (!containsSubset(state.playerXMoves) && !containsSubset(state.playerOMoves) && !state.playground.isEmpty) {

    var movesMade = readLine(s"Available moves are ${state.playground}. What is your move, ${getPlayerTurn(state.isPlayerXTurn)}?").trim.toInt
    if (state.isPlayerXTurn) {
      if (state.playground.contains(movesMade) && (movesMade >= state.minMove) && (movesMade <= state.maxMove))
        state.playerXMoves += movesMade
      println(s"${getPlayerTurn(state.isPlayerXTurn)} has made these moves: ${state.playerXMoves}")
    }

    else {
      if (state.playground.contains(movesMade) && (movesMade >= state.minMove) && (movesMade <= state.maxMove))
        state.playerOMoves += movesMade
      println(s"${getPlayerTurn(state.isPlayerXTurn)} has made these moves: ${state.playerOMoves}")
    }

    if (state.playground.contains(movesMade) && (movesMade >= state.minMove) && (movesMade <= state.maxMove))
      state.isPlayerXTurn = !state.isPlayerXTurn
    state.playground -= movesMade
  }
  if (containsSubset(state.playerXMoves)) {
    println("Player X, you win")
  }
  else if (containsSubset(state.playerOMoves)) {
    println("Player O, you win")
  }
  else println("It is a draw. There is no winner.")

}
