// load program definition json
console.log("Loading program definition");
import program from './helloworld4.json' assert {type: 'json'};

// constants
const MAIN = "main";
const CR = "\n";
const QUOTE = '"';

// method to create code from method definition
const compileMethod = (method) => {
   let code = method.name+": ("+method.parameters.join(",")+") => {"+CR;
   for (var instruction of method.instructions) {
      code += compileInstruction(instruction) + CR;
   }
   console.log("Creating method: "+method);
   switch (method.returns.length) {
      case 0:
        break
      case 1:
        code += "return " + method.returns[0] + CR;
        break
      default:
        code += "return ["+method.returns.join(",")+"]"
   }
   return code + "}," + CR
}

// method to create code for a method call
const constructCall = (instruction, caller) => {
   let prefix = "";
   console.log(instruction.type+": "+instruction.method+" returned: "+instruction.returned);
   switch (instruction.returned.length) {
      case 0:
        break
      case 1:
        prefix = "const " + instruction.returned[0] + " = ";
        break
      default:
        prefix = "const ["+instruction.returned.join(",")+"] = "
   }
   let code = prefix + caller+"."+instruction.method+"("+instruction.arguments.join(",")+");";
   for (var i of instruction.instructions) {
      code += CR + compileInstruction(i);
   }
   return code
}

// method to create code for an object definition
const constructObject = (instruction) => {
   let code = "{" + CR + "const "+instruction.variable+" = {"+CR;
   for (var method of instruction.methods) {
      code += compileMethod(method);
   }
   code += "}" + CR
   for (var i of instruction.instructions) {
      code += compileInstruction(i)+CR;
   }
   return code + "}"
}

// method to create code for an instruction
const compileInstruction = (instruction) => {
    switch (instruction.type) {
      case "literal":
        return "const "+instruction.variable+" = "+QUOTE+instruction.value+QUOTE+";"
      case "native":
        return instruction.script;
      case "mcall":
        return constructCall(instruction, instruction.module)
      case "ocall":
        return constructCall(instruction, instruction.object)
      case "object":
        return constructObject(instruction)
      default:
        return "throw 'unknown instruction type "+instruction.type+"'";
    }
}

// main
{
    console.log("Assembling code for program "+program.name);
    let code = "";
    let modulesWithMain = [];
     // create code for all modules
     for (var module of program.modules) {
       code += "const "+module.name+" = {" + CR;
       // create code for all methods in module
       for (var method of module.methods) {
          code += compileMethod(method);
          if (method.name === MAIN) {
             modulesWithMain.push(module.name)
          }
       }
       code += "}" + CR;
    }
    // add calls of main methods
    for (var module of modulesWithMain) {
       code += module+".main();" + CR
    }
    console.log("----------------------------------------------------------");
    console.log(code);
    console.log("----------------------------------------------------------");
    console.log("Running code");
    eval(code);
    console.log("Done");
}