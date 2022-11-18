/**
 * Copyright © 2018 organization baomidou
 * <pre>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * <pre/>
 */
package com.xtxk.dataSource.model;

/**
 * Druid防火墙配置
 *
 * @author TaoYu
 */
public class DruidWallConfig {


    private Boolean noneBaseStatementAllow;

    private Boolean callAllow;
    private Boolean selectAllow;
    private Boolean selectIntoAllow;
    private Boolean selectIntoOutfileAllow;
    private Boolean selectWhereAlwayTrueCheck;
    private Boolean selectHavingAlwayTrueCheck;
    private Boolean selectUnionCheck;
    private Boolean selectMinusCheck;
    private Boolean selectExceptCheck;
    private Boolean selectIntersectCheck;
    private Boolean createTableAllow;
    private Boolean dropTableAllow;
    private Boolean alterTableAllow;
    private Boolean renameTableAllow;
    private Boolean hintAllow;
    private Boolean lockTableAllow;
    private Boolean startTransactionAllow;
    private Boolean blockAllow;

    private Boolean conditionAndAlwayTrueAllow;
    private Boolean conditionAndAlwayFalseAllow;
    private Boolean conditionDoubleConstAllow;
    private Boolean conditionLikeTrueAllow;

    private Boolean selectAllColumnAllow;

    private Boolean deleteAllow;
    private Boolean deleteWhereAlwayTrueCheck;
    private Boolean deleteWhereNoneCheck;

    private Boolean updateAllow;
    private Boolean updateWhereAlayTrueCheck;
    private Boolean updateWhereNoneCheck;

    private Boolean insertAllow;
    private Boolean mergeAllow;
    private Boolean minusAllow;
    private Boolean intersectAllow;
    private Boolean replaceAllow;
    private Boolean setAllow;
    private Boolean commitAllow;
    private Boolean rollbackAllow;
    private Boolean useAllow;

    private Boolean multiStatementAllow;

    private Boolean truncateAllow;

    private Boolean commentAllow;
    private Boolean strictSyntaxCheck;
    private Boolean constArithmeticAllow;
    private Boolean limitZeroAllow;

    private Boolean describeAllow;
    private Boolean showAllow;

    private Boolean schemaCheck;
    private Boolean tableCheck;
    private Boolean functionCheck;
    private Boolean objectCheck;
    private Boolean variantCheck;

    private Boolean mustParameterized;

    private Boolean doPrivilegedAllow;

    private String dir;

    private String tenantTablePattern;
    private String tenantColumn;

    private Boolean wrapAllow;
    private Boolean metadataAllow;

    private Boolean conditionOpXorAllow;
    private Boolean conditionOpBitwseAllow;

    private Boolean caseConditionConstAllow;

    private Boolean completeInsertValuesCheck;
    private Integer insertValuesCheckSize;

    private Integer selectLimit;

    public Boolean getNoneBaseStatementAllow() {
        return noneBaseStatementAllow;
    }

    public void setNoneBaseStatementAllow(Boolean noneBaseStatementAllow) {
        this.noneBaseStatementAllow = noneBaseStatementAllow;
    }

    public Boolean getCallAllow() {
        return callAllow;
    }

    public void setCallAllow(Boolean callAllow) {
        this.callAllow = callAllow;
    }

    public Boolean getSelectAllow() {
        return selectAllow;
    }

    public void setSelectAllow(Boolean selectAllow) {
        this.selectAllow = selectAllow;
    }

    public Boolean getSelectIntoAllow() {
        return selectIntoAllow;
    }

    public void setSelectIntoAllow(Boolean selectIntoAllow) {
        this.selectIntoAllow = selectIntoAllow;
    }

    public Boolean getSelectIntoOutfileAllow() {
        return selectIntoOutfileAllow;
    }

    public void setSelectIntoOutfileAllow(Boolean selectIntoOutfileAllow) {
        this.selectIntoOutfileAllow = selectIntoOutfileAllow;
    }

    public Boolean getSelectWhereAlwayTrueCheck() {
        return selectWhereAlwayTrueCheck;
    }

    public void setSelectWhereAlwayTrueCheck(Boolean selectWhereAlwayTrueCheck) {
        this.selectWhereAlwayTrueCheck = selectWhereAlwayTrueCheck;
    }

    public Boolean getSelectHavingAlwayTrueCheck() {
        return selectHavingAlwayTrueCheck;
    }

    public void setSelectHavingAlwayTrueCheck(Boolean selectHavingAlwayTrueCheck) {
        this.selectHavingAlwayTrueCheck = selectHavingAlwayTrueCheck;
    }

    public Boolean getSelectUnionCheck() {
        return selectUnionCheck;
    }

    public void setSelectUnionCheck(Boolean selectUnionCheck) {
        this.selectUnionCheck = selectUnionCheck;
    }

    public Boolean getSelectMinusCheck() {
        return selectMinusCheck;
    }

    public void setSelectMinusCheck(Boolean selectMinusCheck) {
        this.selectMinusCheck = selectMinusCheck;
    }

    public Boolean getSelectExceptCheck() {
        return selectExceptCheck;
    }

    public void setSelectExceptCheck(Boolean selectExceptCheck) {
        this.selectExceptCheck = selectExceptCheck;
    }

    public Boolean getSelectIntersectCheck() {
        return selectIntersectCheck;
    }

    public void setSelectIntersectCheck(Boolean selectIntersectCheck) {
        this.selectIntersectCheck = selectIntersectCheck;
    }

    public Boolean getCreateTableAllow() {
        return createTableAllow;
    }

    public void setCreateTableAllow(Boolean createTableAllow) {
        this.createTableAllow = createTableAllow;
    }

    public Boolean getDropTableAllow() {
        return dropTableAllow;
    }

    public void setDropTableAllow(Boolean dropTableAllow) {
        this.dropTableAllow = dropTableAllow;
    }

    public Boolean getAlterTableAllow() {
        return alterTableAllow;
    }

    public void setAlterTableAllow(Boolean alterTableAllow) {
        this.alterTableAllow = alterTableAllow;
    }

    public Boolean getRenameTableAllow() {
        return renameTableAllow;
    }

    public void setRenameTableAllow(Boolean renameTableAllow) {
        this.renameTableAllow = renameTableAllow;
    }

    public Boolean getHintAllow() {
        return hintAllow;
    }

    public void setHintAllow(Boolean hintAllow) {
        this.hintAllow = hintAllow;
    }

    public Boolean getLockTableAllow() {
        return lockTableAllow;
    }

    public void setLockTableAllow(Boolean lockTableAllow) {
        this.lockTableAllow = lockTableAllow;
    }

    public Boolean getStartTransactionAllow() {
        return startTransactionAllow;
    }

    public void setStartTransactionAllow(Boolean startTransactionAllow) {
        this.startTransactionAllow = startTransactionAllow;
    }

    public Boolean getBlockAllow() {
        return blockAllow;
    }

    public void setBlockAllow(Boolean blockAllow) {
        this.blockAllow = blockAllow;
    }

    public Boolean getConditionAndAlwayTrueAllow() {
        return conditionAndAlwayTrueAllow;
    }

    public void setConditionAndAlwayTrueAllow(Boolean conditionAndAlwayTrueAllow) {
        this.conditionAndAlwayTrueAllow = conditionAndAlwayTrueAllow;
    }

    public Boolean getConditionAndAlwayFalseAllow() {
        return conditionAndAlwayFalseAllow;
    }

    public void setConditionAndAlwayFalseAllow(Boolean conditionAndAlwayFalseAllow) {
        this.conditionAndAlwayFalseAllow = conditionAndAlwayFalseAllow;
    }

    public Boolean getConditionDoubleConstAllow() {
        return conditionDoubleConstAllow;
    }

    public void setConditionDoubleConstAllow(Boolean conditionDoubleConstAllow) {
        this.conditionDoubleConstAllow = conditionDoubleConstAllow;
    }

    public Boolean getConditionLikeTrueAllow() {
        return conditionLikeTrueAllow;
    }

    public void setConditionLikeTrueAllow(Boolean conditionLikeTrueAllow) {
        this.conditionLikeTrueAllow = conditionLikeTrueAllow;
    }

    public Boolean getSelectAllColumnAllow() {
        return selectAllColumnAllow;
    }

    public void setSelectAllColumnAllow(Boolean selectAllColumnAllow) {
        this.selectAllColumnAllow = selectAllColumnAllow;
    }

    public Boolean getDeleteAllow() {
        return deleteAllow;
    }

    public void setDeleteAllow(Boolean deleteAllow) {
        this.deleteAllow = deleteAllow;
    }

    public Boolean getDeleteWhereAlwayTrueCheck() {
        return deleteWhereAlwayTrueCheck;
    }

    public void setDeleteWhereAlwayTrueCheck(Boolean deleteWhereAlwayTrueCheck) {
        this.deleteWhereAlwayTrueCheck = deleteWhereAlwayTrueCheck;
    }

    public Boolean getDeleteWhereNoneCheck() {
        return deleteWhereNoneCheck;
    }

    public void setDeleteWhereNoneCheck(Boolean deleteWhereNoneCheck) {
        this.deleteWhereNoneCheck = deleteWhereNoneCheck;
    }

    public Boolean getUpdateAllow() {
        return updateAllow;
    }

    public void setUpdateAllow(Boolean updateAllow) {
        this.updateAllow = updateAllow;
    }

    public Boolean getUpdateWhereAlayTrueCheck() {
        return updateWhereAlayTrueCheck;
    }

    public void setUpdateWhereAlayTrueCheck(Boolean updateWhereAlayTrueCheck) {
        this.updateWhereAlayTrueCheck = updateWhereAlayTrueCheck;
    }

    public Boolean getUpdateWhereNoneCheck() {
        return updateWhereNoneCheck;
    }

    public void setUpdateWhereNoneCheck(Boolean updateWhereNoneCheck) {
        this.updateWhereNoneCheck = updateWhereNoneCheck;
    }

    public Boolean getInsertAllow() {
        return insertAllow;
    }

    public void setInsertAllow(Boolean insertAllow) {
        this.insertAllow = insertAllow;
    }

    public Boolean getMergeAllow() {
        return mergeAllow;
    }

    public void setMergeAllow(Boolean mergeAllow) {
        this.mergeAllow = mergeAllow;
    }

    public Boolean getMinusAllow() {
        return minusAllow;
    }

    public void setMinusAllow(Boolean minusAllow) {
        this.minusAllow = minusAllow;
    }

    public Boolean getIntersectAllow() {
        return intersectAllow;
    }

    public void setIntersectAllow(Boolean intersectAllow) {
        this.intersectAllow = intersectAllow;
    }

    public Boolean getReplaceAllow() {
        return replaceAllow;
    }

    public void setReplaceAllow(Boolean replaceAllow) {
        this.replaceAllow = replaceAllow;
    }

    public Boolean getSetAllow() {
        return setAllow;
    }

    public void setSetAllow(Boolean setAllow) {
        this.setAllow = setAllow;
    }

    public Boolean getCommitAllow() {
        return commitAllow;
    }

    public void setCommitAllow(Boolean commitAllow) {
        this.commitAllow = commitAllow;
    }

    public Boolean getRollbackAllow() {
        return rollbackAllow;
    }

    public void setRollbackAllow(Boolean rollbackAllow) {
        this.rollbackAllow = rollbackAllow;
    }

    public Boolean getUseAllow() {
        return useAllow;
    }

    public void setUseAllow(Boolean useAllow) {
        this.useAllow = useAllow;
    }

    public Boolean getMultiStatementAllow() {
        return multiStatementAllow;
    }

    public void setMultiStatementAllow(Boolean multiStatementAllow) {
        this.multiStatementAllow = multiStatementAllow;
    }

    public Boolean getTruncateAllow() {
        return truncateAllow;
    }

    public void setTruncateAllow(Boolean truncateAllow) {
        this.truncateAllow = truncateAllow;
    }

    public Boolean getCommentAllow() {
        return commentAllow;
    }

    public void setCommentAllow(Boolean commentAllow) {
        this.commentAllow = commentAllow;
    }

    public Boolean getStrictSyntaxCheck() {
        return strictSyntaxCheck;
    }

    public void setStrictSyntaxCheck(Boolean strictSyntaxCheck) {
        this.strictSyntaxCheck = strictSyntaxCheck;
    }

    public Boolean getConstArithmeticAllow() {
        return constArithmeticAllow;
    }

    public void setConstArithmeticAllow(Boolean constArithmeticAllow) {
        this.constArithmeticAllow = constArithmeticAllow;
    }

    public Boolean getLimitZeroAllow() {
        return limitZeroAllow;
    }

    public void setLimitZeroAllow(Boolean limitZeroAllow) {
        this.limitZeroAllow = limitZeroAllow;
    }

    public Boolean getDescribeAllow() {
        return describeAllow;
    }

    public void setDescribeAllow(Boolean describeAllow) {
        this.describeAllow = describeAllow;
    }

    public Boolean getShowAllow() {
        return showAllow;
    }

    public void setShowAllow(Boolean showAllow) {
        this.showAllow = showAllow;
    }

    public Boolean getSchemaCheck() {
        return schemaCheck;
    }

    public void setSchemaCheck(Boolean schemaCheck) {
        this.schemaCheck = schemaCheck;
    }

    public Boolean getTableCheck() {
        return tableCheck;
    }

    public void setTableCheck(Boolean tableCheck) {
        this.tableCheck = tableCheck;
    }

    public Boolean getFunctionCheck() {
        return functionCheck;
    }

    public void setFunctionCheck(Boolean functionCheck) {
        this.functionCheck = functionCheck;
    }

    public Boolean getObjectCheck() {
        return objectCheck;
    }

    public void setObjectCheck(Boolean objectCheck) {
        this.objectCheck = objectCheck;
    }

    public Boolean getVariantCheck() {
        return variantCheck;
    }

    public void setVariantCheck(Boolean variantCheck) {
        this.variantCheck = variantCheck;
    }

    public Boolean getMustParameterized() {
        return mustParameterized;
    }

    public void setMustParameterized(Boolean mustParameterized) {
        this.mustParameterized = mustParameterized;
    }

    public Boolean getDoPrivilegedAllow() {
        return doPrivilegedAllow;
    }

    public void setDoPrivilegedAllow(Boolean doPrivilegedAllow) {
        this.doPrivilegedAllow = doPrivilegedAllow;
    }

    public String getDir() {
        return dir;
    }

    public void setDir(String dir) {
        this.dir = dir;
    }

    public String getTenantTablePattern() {
        return tenantTablePattern;
    }

    public void setTenantTablePattern(String tenantTablePattern) {
        this.tenantTablePattern = tenantTablePattern;
    }

    public String getTenantColumn() {
        return tenantColumn;
    }

    public void setTenantColumn(String tenantColumn) {
        this.tenantColumn = tenantColumn;
    }

    public Boolean getWrapAllow() {
        return wrapAllow;
    }

    public void setWrapAllow(Boolean wrapAllow) {
        this.wrapAllow = wrapAllow;
    }

    public Boolean getMetadataAllow() {
        return metadataAllow;
    }

    public void setMetadataAllow(Boolean metadataAllow) {
        this.metadataAllow = metadataAllow;
    }

    public Boolean getConditionOpXorAllow() {
        return conditionOpXorAllow;
    }

    public void setConditionOpXorAllow(Boolean conditionOpXorAllow) {
        this.conditionOpXorAllow = conditionOpXorAllow;
    }

    public Boolean getConditionOpBitwseAllow() {
        return conditionOpBitwseAllow;
    }

    public void setConditionOpBitwseAllow(Boolean conditionOpBitwseAllow) {
        this.conditionOpBitwseAllow = conditionOpBitwseAllow;
    }

    public Boolean getCaseConditionConstAllow() {
        return caseConditionConstAllow;
    }

    public void setCaseConditionConstAllow(Boolean caseConditionConstAllow) {
        this.caseConditionConstAllow = caseConditionConstAllow;
    }

    public Boolean getCompleteInsertValuesCheck() {
        return completeInsertValuesCheck;
    }

    public void setCompleteInsertValuesCheck(Boolean completeInsertValuesCheck) {
        this.completeInsertValuesCheck = completeInsertValuesCheck;
    }

    public Integer getInsertValuesCheckSize() {
        return insertValuesCheckSize;
    }

    public void setInsertValuesCheckSize(Integer insertValuesCheckSize) {
        this.insertValuesCheckSize = insertValuesCheckSize;
    }

    public Integer getSelectLimit() {
        return selectLimit;
    }

    public void setSelectLimit(Integer selectLimit) {
        this.selectLimit = selectLimit;
    }
}