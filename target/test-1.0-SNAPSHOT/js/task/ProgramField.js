/**
 * Created by acer on 05.01.2017.
 */
function ProgramField(width, height) {
    this.begin = '01  begin';
    this.end = 'end.';
    this.numberOfCurrentString = 1; //номер очередной строки
    this.nFor = 0; //кол-во итераций цикла for
    this.isProcedure = false; //является ли блок процедурой
    this.isForBlock = false; //является ли блок циклическим
    this.procedure = 'procedure Proc;' + '\n' + 'begin';


    this.addOnDisplay = function () {
        $('.canvas').last().after('<div id="textarea"><textarea id="program_filed" rows="' + height + '" cols="' + width + '" readonly></textarea></div>');
        $('#program_filed').val(this.begin + '\n' + '02  ' + this.end);
    };

    this.addText = function (command) {
        var factory = new CommandFactory(command);
        if (command === CommandEnum.FOR) {
            factory.getCommand().setNFor(this.nFor);
        }

        var text = factory.getTextForProgramField();

        if (this.isForBlock) {
            text = '  ' + text;
            this.isForBlock = false;
        }

        this.numberOfCurrentString++;
        if (this.numberOfCurrentString > 0 && this.numberOfCurrentString < 10)
            this.begin = this.begin + '\n' + '0' + this.numberOfCurrentString + '  ' + '  ' + text;
        else
            this.begin = this.begin + '\n' + this.numberOfCurrentString + '  ' + '  ' + text;
        $('#program_filed').val('');
        var procedure = '';
        if (this.isProcedure) {
            procedure = this.procedure + '\n' + 'end;' + '\n' + '\n';
        }
        if (this.numberOfCurrentString + 1 > 0 && this.numberOfCurrentString + 1 < 10)
            $('#program_filed').val(procedure + this.begin + '\n' + '0' + (this.numberOfCurrentString + 1) + '  ' + this.end);
        else
            $('#program_filed').val(procedure + this.begin + '\n' + (this.numberOfCurrentString + 1) + '  ' + this.end);
    };

    this.addTextToProcedure = function (command) {
        $('#program_filed').val('');
        this.procedure = this.procedure + '\n' + '  ' + command + ';';
        var procedure = this.procedure + '\n' + 'end;' + '\n' + '\n';
        if (this.numberOfCurrentString + 1 > 0 && this.numberOfCurrentString + 1 < 10)
            $('#program_filed').val(procedure + this.begin + "\n" + "0" + (this.numberOfCurrentString + 1) + "  " + this.end);
        else
            $('#program_filed').val(procedure + this.begin + "\n" + (this.numberOfCurrentString + 1) + "  " + this.end);
    };

    this.removeString = function () {
        var newString = '';
        var i = this.begin.length - 1;
        while (i >= 0 && this.begin[i] != '\n')
            i--;
        i--;
        for (var j = i; j >= 0; j--)
            newString = this.begin[j] + newString;
        this.numberOfCurrentString--;
        this.begin = newString;
        $('#program_filed').val('');
        var procedure = '';
        if (this.isProcedure)
            procedure = this.procedure + '\n' + 'end;' + '\n' + '\n';
        if (this.numberOfCurrentString + 1 > 0 && this.numberOfCurrentString + 1 < 10)
            $('#program_filed').val(procedure + this.begin + "\n" + "0" + (this.numberOfCurrentString + 1) + "  " + this.end);
        else
            $('#program_filed').val(procedure + this.begin + "\n" + (this.numberOfCurrentString + 1) + "  " + this.end);

    };

    this.removeStringFromProcedure = function () {
        var newString = '';
        var i = this.procedure.length - 1;
        while (i >= 0 && this.procedure[i] != '\n')
            i--;
        i--;
        for (var j = i; j >= 0; j--)
            newString = this.procedure[j] + newString;
        this.procedure = newString;
        $('#program_filed').val('');
        var procedure = this.procedure + '\n' + 'end;' + '\n' + '\n';
        if (this.numberOfCurrentString + 1 > 0 && this.numberOfCurrentString + 1 < 10)
            $('#program_filed').val(procedure + this.begin + "\n" + "0" + (this.numberOfCurrentString + 1) + "  " + this.end);
        else
            $('#program_filed').val(procedure + this.begin + "\n" + (this.numberOfCurrentString + 1) + "  " + this.end);
    };

    this.setNFor = function (n) {
        this.nFor = n;
    };

    this.setProcedure = function (b) {
        this.isProcedure = b;
        if (this.isProcedure) {
            $('#program_filed').val('');
            $('#program_filed').val(this.procedure + '\n' + 'end;' + '\n' + '\n' + this.begin + '\n' + '02  ' + this.end);
        }
    };

    this.setForBlock = function (isForBlock) {
        this.isForBlock = isForBlock;
    }

}