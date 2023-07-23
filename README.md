
# INTRO  Project #

## Purpose ##

The INTRO project is an experiment of establishing a global network for storing and processing information in an integrative fashion.

## The layers ##

### Storage Layer ###

Data storage always comes whith two aspects: WHAT is stored and WHERE is it stored. 
The basis of the INTRO project is to completely hide the WHERE aspect… Data will be stored redundantly 
on multiple servers across the world (for reasons of access performance and rebustness), but this does
not become visible to higher layers. Data is purely accessed by specifing the WHAT aspect.

Data in INTRO is stored in two principle ways:

 * Immutable data: constant arrays of bytes are hashed. They can exclusively be accessed via their hash. 
   This data can obviously not be changed or renamed (it is however possible to modify some parts of the data 
   array and store it as a new data item, with a new has). Deletion of data is not possible in INTRO, once
   created and hashed, the data array is considered to be stored eternally. However, due to capacity limits
   of the network, it will happen that rarely accessed data elements will not be present anymore on any server.  
   Effectively this means that the data array is lost and cannot be accessed anymore.
 * The Global Key Value Map: A global map from String keys (only lowercase alphanumeric with dashes and dots)
   to hashes of immutable data is maintained and made accessible to all participants. Read-access to the map 
   is possible without restriction for each INTRO participant, but writing/deleting entries in the map is 
   restricted via a permission model that is introduced in phase 2 (see below for description) of phases.

### Processing Layer ###

### UserInterface Layer ###

## The phases ##

Roll-out and development will be done in four phases:

### Phase 1: POC on individual level ###

In the first stage all development is done by one individual. In this proof-of-concept phase, the 
feasibility and elementary features to experience and play with the INTRO network will be created.

Networking and storage components are simple stubs, no multi-server, multi-user, collaborative and security 
aspects will be addressed in this phase.  

### Phase 2: Group of trusted pioneers ###

In the second stage, the INTRO network will be opened to a group of collaborators. It will be assumed
that participants cooperate in good will and can be trusted.

Networking and storage components are extended towards multi-server, multi-user use and support collaborative
processes. Security, privacy and robustness/performance aspects will be not be addressed in this phase.  

### Phase 3: Security and privacy ###

In the third stage, the INTRO network will hardened against misbehaviour of participants and 
maliciously acting network components. The aspects safety of data (against deletion or accidental loss) and privacy
of data (against leaking to non authorized participants) will be not be addressed in this phase.  

### Phase 4: Robustness and scaling ###

In the forth stage, the INTRO network will prepared for keeping performance when scaling up 
as well as robustness/redundance to be able to tolerate hardware or network failures. 